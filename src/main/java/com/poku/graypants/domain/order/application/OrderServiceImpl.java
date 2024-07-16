package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderRepository;
import com.poku.graypants.domain.orderItem.application.OrderItemDataService;
import com.poku.graypants.domain.orderItem.application.OrderItemService;
import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 주문 서비스 구현체
 *
 * @version 1.0.0
 * @Author Jgone2
 * @see OrderService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StoreService storeService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final ItemService itemService;


    @Override
    @Transactional
    public void createOrder(OrderCreateRequestDto orderCreateRequestDto, Long userId) {
        Order savedOrder = orderRepository.save(orderCreateRequestDto.toEntity(userService.getUserByUserId(userId)));
        for(int i = 0; i < orderCreateRequestDto.getItemIdList().size(); i++) {
            orderItemService.createOrderItem(savedOrder, itemService.getVerifyItemById(orderCreateRequestDto.getItemIdList().get(i)),
                    orderCreateRequestDto.getItemQuantityList().get(i),
                    OrderItemStatus.COMPLETE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(Long orderId) {
        return new OrderResponseDto(getVerifyExistsOrder(orderId));
    }

    @Override
    public Order getVerifyOrderByOrderId(Long orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        List<Order> findAllOrders = orderRepository.findAllByUser_UserId(userId);
        return findAllOrders.stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long orderId, OrderUpdateRequestDto orderUpdateRequestDto, Long userId) {
        User findUser = userService.getUser(userId);
        Order findOrder = getVerifyExistsOrder(orderId);

        verifyOrderUserAndLoginUserMatch(findOrder, findUser);

        findOrder.updateOrder(orderUpdateRequestDto);
        return new OrderResponseDto(findOrder);
    }

    /**
     * 주문 삭제
     *
     * @param orderId
     * @return void
     * @throws GrayPantsException 주문이 존재하지 않을 경우 개선 사항 -> 추후 하드삭제가 아닌 소프트 삭제로 변경(삭제여부 컬럼 추가)
     */
    @Override
    @Transactional
    public void deleteOrder(Long orderId, Long userId) {
        Order verifyExistsOrder = getVerifyExistsOrder(orderId);
        User findUser = userService.getUser(userId);
        verifyOrderUserAndLoginUserMatch(verifyExistsOrder, findUser);
        orderRepository.delete(verifyExistsOrder);
    }

    @Transactional(readOnly = true)
    public Order getVerifyExistsOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_NOT_FOUND));
    }

    private void verifyOrderUserAndLoginUserMatch(Order findOrder, User findUser) {
        if (!findOrder.getUserId().equals(findUser.getUserId())) {
            throw new GrayPantsException(ExceptionStatus.ORDER_AND_USER_MISMATCH);
        }
    }
}

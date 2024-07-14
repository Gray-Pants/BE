package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.OrderServiceImpl;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemCreateRequestDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemUpdateRequestDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemRepository;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderServiceImpl orderService;
    private final ItemService itemService;
    private final StoreService storeService;

    @Override
    public OrderItemResponseDto createOrderItem(OrderCreateRequestDto orderCreateRequestDto, Long userId,
                                                OrderItemCreateRequestDto orderItemCreateRequestDto, Long itemId) {
        OrderResponseDto createdOrder = orderService.createOrder(orderCreateRequestDto, userId);
        Order verifyExistsOrder = orderService.getVerifyExistsOrder(createdOrder.getOrderId());
        Item findItem = itemService.getVerifyItemById(itemId);
        Store findStore = storeService.getVerifyStore(findItem.getStore().getStoreEmail());

        OrderItem createOrderItem = orderItemCreateRequestDto.toEntity(verifyExistsOrder, findItem, findStore);
        OrderItem savedOrderItem = orderItemRepository.save(createOrderItem);
        return new OrderItemResponseDto(savedOrderItem);
    }

    @Override
    public OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto) {
        OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
        verifyOrderItem.updateOrderItem(orderItemUpdateRequestDto);
        return new OrderItemResponseDto(verifyOrderItem);
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrder(Order order) {
        return order.getOrderItems().stream().map(OrderItemResponseDto::new).toList();
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
        orderItemRepository.delete(verifyOrderItem);
    }

    @Override
    public OrderItem getVerifyOrderItemByOrderItemId(Long orderItemId) {
        return orderItemRepository.findByOrderItemId(orderItemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_ITEM_NOT_FOUND));
    }


    private OrderItem getVerifyOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_ITEM_NOT_FOUND));
    }
}

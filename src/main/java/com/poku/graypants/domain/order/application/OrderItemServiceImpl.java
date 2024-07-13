package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.dto.*;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderItem;
import com.poku.graypants.domain.order.persistence.OrderItemRepository;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

  private final OrderItemRepository orderItemRepository;
  private final OrderServiceImpl orderService;
  private final ItemService itemService;
  private final StoreService storeService;
  private final UserService userService;

  @Override
  public OrderItemResponseDto createOrderItem(OrderCreateRequestDto orderCreateRequestDto, OrderItemCreateRequestDto orderItemCreateRequestDto, Long userId, Long itemId) {
    User findUser = userService.getUser(userId);
    Order order = orderCreateRequestDto.toEntity(findUser.getUserId();
    OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateRequestDto, findUser.getUserId());

    Item findItem = itemService.getVerifyItemById(itemId);
    Store findStore = storeService.getVerifyStore(findItem.getStore().getStoreEmail());

    itemService.verifyItemAndStoreMatch(findItem, findStore);

    // 유저 정보 검증
    orderService.verifyOrderUserAndLoginUserMatch(order, userService.getUser(userId));

    OrderItem createOrderItem = orderItemCreateRequestDto.toEntity(order, findItem, findStore);
    OrderItem savedOrderItem = orderItemRepository.save(createOrderItem);

    return new OrderItemResponseDto(savedOrderItem, orderResponseDto);
  }

  @Override
  public List<OrderItemResponseDto> createOrderItems(Order order, Long userId, List<OrderItemCreateRequestDto> orderItemCreateRequestDtos, List<Long> itemIdList) {
//    List<OrderItemResponseDto> result = new ArrayList<>();
//    int index = 0;
//    for (OrderItemCreateRequestDto orderItemCreateRequestDto : orderItemCreateRequestDtos) {
//      Item findItem = itemService.getItemById(orderItemCreateRequestDto.getItemId());
//      Store findStore = storeService.getVerifyStore(findItem.getStore().getStoreEmail());
//
//      itemService.verifyItemAndStoreMatch(findItem, findStore);
//
//      OrderItem createOrderItem = orderItemCreateRequestDto.toEntity(order, findItem, findStore);
//      OrderItem savedOrderItem = orderItemRepository.save(createOrderItem);
//      result.add(new OrderItemResponseDto(savedOrderItem));
//    }
    return null;
  }

  public PaymentRequestDto createPaymentRequestFromOrderItems(OrderResponseDto orderResponseDto, List<OrderItem> orderItems) {
    PaymentRequestDto requestDto = new PaymentRequestDto();

    // 주문 금액 계산
    BigDecimal totalAmount = orderItems.stream()
            .map(item -> BigDecimal.valueOf(item.getOrderItemPrice() * item.getOrderItemQuantity()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    requestDto.setAmount(totalAmount);

    // 상품 이름 설정 (첫 번째 상품 이름으로 설정, 필요에 따라 수정)
    requestDto.setName(orderItems.get(0).getItem().getItemName());

    // user_id 정보 설정
    requestDto.setCustom_data(new PaymentRequestDto.CustomDataDto(orderItems.get(0).getOrder().getUser().getUserId()));

    // orderItem 정보 설정
    requestDto.setOrderItems(orderItems.stream()
            .map(orderItem -> new OrderItemResponseDto(orderItem, orderResponseDto)) // OrderResponseDto 전달
            .collect(Collectors.toList()));

    return requestDto;
  }


  @Override
  public OrderItemResponseDto getOrderItem(Long orderItemId, OrderResponseDto orderResponseDto) {
    return new OrderItemResponseDto(getVerifyOrderItem(orderItemId), orderResponseDto);
  }

  @Override
  public OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto) {
    OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
    verifyOrderItem.updateOrderItem(orderItemUpdateRequestDto);
    return new OrderItemResponseDto(verifyOrderItem, new OrderResponseDto(verifyOrderItem.getOrder()));
  }

  @Override
  public void deleteOrderItem(Long orderItemId) {
    OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
    orderItemRepository.delete(verifyOrderItem);
  }

  private OrderItem getVerifyOrderItem(Long orderItemId) {
    return orderItemRepository.findById(orderItemId).orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_ITEM_NOT_FOUND));
  }
}
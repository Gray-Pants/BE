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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

  private final OrderItemRepository orderItemRepository;
  private final OrderServiceImpl orderService;
  private final ItemService itemService;
  private final StoreService storeService;

  @Override
  public OrderItemResponseDto createOrderItem(OrderCreateRequestDto orderCreateRequestDto, Long userId, OrderItemCreateRequestDto orderItemCreateRequestDto, Long itemId) {
    OrderResponseDto createdOrder = orderService.createOrder(orderCreateRequestDto, userId);
    Order verifyExistsOrder = orderService.getVerifyExistsOrder(createdOrder.getOrderId());
    Item findItem = itemService.getItemById(itemId);
    Store findStore = storeService.getVerifyStore(findItem.getStore().getStoreEmail());

//    itemService.verifyItemAndStoreMatch(findItem, findStore);

    OrderItem createOrderItem = orderItemCreateRequestDto.toEntity(verifyExistsOrder, findItem, findStore);
    OrderItem savedOrderItem = orderItemRepository.save(createOrderItem);
    return new OrderItemResponseDto(savedOrderItem, createdOrder);
  }

  @Override
  public List<OrderItemResponseDto> createOrderItems(OrderCreateRequestDto orderCreateRequestDto, Long userId, List<OrderItemCreateRequestDto> orderItemCreateRequestDtos, Long itemId) {
    OrderResponseDto createdOrder = orderService.createOrder(orderCreateRequestDto, userId);
    Order verifyExistsOrder = orderService.getVerifyExistsOrder(createdOrder.getOrderId());
    for (OrderItemCreateRequestDto orderItemCreateRequestDto : orderItemCreateRequestDtos) {
      Item findItem = itemService.getItemById(itemId);
      Store findStore = storeService.getVerifyStore(findItem.getStore().getStoreEmail());
    }
    return null;
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

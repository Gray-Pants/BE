package com.poku.graypants.domain.orderItem.application.dto;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import com.poku.graypants.domain.store.persistence.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemCreateRequestDto {

  private int orderItemPrice;
  private int orderItemQuantity;
  private OrderItemStatus orderItemStatus;

  public OrderItem toEntity(Order order, Item item, Store store) {
    return OrderItem.builder()
        .orderItemPrice(orderItemPrice)
        .orderItemQuantity(orderItemQuantity)
        .orderItemStatus(orderItemStatus)
        .order(order)
        .item(item)
        .store(store)
        .build();
  }
}

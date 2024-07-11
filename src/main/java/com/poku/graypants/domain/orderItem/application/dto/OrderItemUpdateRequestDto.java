package com.poku.graypants.domain.orderItem.application.dto;

import com.poku.graypants.domain.order.persistence.OrderItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemUpdateRequestDto {
  private int orderItemQuantity;
  private OrderItemStatus orderItemStatus;
}

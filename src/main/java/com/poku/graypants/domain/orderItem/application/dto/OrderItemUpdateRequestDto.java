package com.poku.graypants.domain.orderItem.application.dto;

import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemUpdateRequestDto {
  private OrderItemStatus orderItemStatus;
}

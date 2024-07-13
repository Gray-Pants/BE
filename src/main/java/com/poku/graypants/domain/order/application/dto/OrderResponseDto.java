package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseDto {

  private final Long orderId;
  private final String orderAddr;
  private final String orderPhone;
  private final String userName;
  private final List<OrderItemResponseDto> orderItems;
  private final LocalDateTime created_at;
  private final LocalDateTime updated_at;

  @Builder
  public OrderResponseDto(Order order) {
    this.orderId = order.getOrderId();
    this.orderAddr = order.getOrderAddr();
    this.orderPhone = order.getOrderPhone();
    this.userName = order.getUser().getUsername();
    this.orderItems = order.getOrderItems();
    this.created_at = order.getCreatedAt();
    this.updated_at = order.getUpdatedAt();
  }
}

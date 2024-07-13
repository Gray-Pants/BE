package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseDto {

  private Long orderId;
  private String orderAddr;
  private String orderPhone;
  private String userName;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  @Builder
  public OrderResponseDto(Order order) {
    this.orderId = order.getOrderId();
    this.orderAddr = order.getOrderAddr();
    this.orderPhone = order.getOrderPhone();
    this.userName = order.getUser().getUsername();
    this.created_at = order.getCreatedAt();
    this.updated_at = order.getUpdatedAt();
  }

  public OrderResponseDto(Order order, String jwtToken) {
    this.orderId = order.getOrderId();
    this.orderAddr = order.getOrderAddr();
    this.orderPhone = order.getOrderPhone();
    this.userName = order.getUser().getUsername();
    this.created_at = order.getCreatedAt();
    this.updated_at = order.getUpdatedAt();
  }
}

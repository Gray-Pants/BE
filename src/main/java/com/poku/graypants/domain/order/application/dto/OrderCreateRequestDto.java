package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderStatus;
import com.poku.graypants.domain.user.persistence.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto {

  private String tid;
  private String orderAddr;
  private String orderPhone;
  private OrderStatus orderStatus;
  private Integer totalAmount;

  @Builder
  public OrderCreateRequestDto(String tid, String orderAddr, String orderPhone, OrderStatus orderStatus, Integer totalAmount) {
    this.tid = tid;
    this.orderAddr = orderAddr;
    this.orderPhone = orderPhone;
    this.orderStatus = orderStatus;
    this.totalAmount = totalAmount;
  }

  public Order toEntity(Long userId){
    return Order.builder()
            .orderStatus(orderStatus)
            .orderAddr(orderAddr)
            .orderPhone(orderPhone)
            .userId(userId)
            .tid(tid)
            .totalAmount(totalAmount)
            .build();
  }
}
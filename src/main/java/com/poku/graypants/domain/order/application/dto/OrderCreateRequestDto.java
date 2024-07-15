package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderStatus;
import com.poku.graypants.domain.user.persistence.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto {

  private String tid;
  private String orderAddr;
  private String orderPhone;
  private OrderStatus orderStatus;
  private Integer totalAmount;
  private List<Long> itemIdList;
  private List<Integer> itemQuantityList;

  @Builder
  public OrderCreateRequestDto(List<Long> itemIdList, List<Integer> itemQuantityList, String tid, String orderAddr, String orderPhone, OrderStatus orderStatus, Integer totalAmount) {
    this.itemIdList = itemIdList;
    this.itemQuantityList = itemQuantityList;
    this.tid = tid;
    this.orderAddr = orderAddr;
    this.orderPhone = orderPhone;
    this.orderStatus = orderStatus;
    this.totalAmount = totalAmount;
  }

  public Order toEntity(User user){
    return Order.builder()
            .orderStatus(orderStatus)
            .orderAddr(orderAddr)
            .orderPhone(orderPhone)
            .user(user)
            .tid(tid)
            .totalAmount(totalAmount)
            .build();
  }
}
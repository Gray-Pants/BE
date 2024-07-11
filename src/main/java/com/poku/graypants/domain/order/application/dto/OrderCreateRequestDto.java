package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.user.persistence.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto {

  private String orderAddr;
  private String orderPhone;

  public Order toEntity(User user){
    return Order.builder()
            .orderAddr(orderAddr)
            .orderPhone(orderPhone)
            .user(user)
            .build();
  }
}
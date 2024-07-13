package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.OrderItem;
import com.poku.graypants.domain.order.persistence.OrderItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {

  private int orderItemPrice;
  private int orderItemQuantity;
  private OrderItemStatus orderItemStatus;
  private Long orderId;
  private Long itemId;
  private String storeName;

  private String cardNumber;   // 카드 번호 추가
  private String expiry;       // 카드 유효 기간 추가
  private String birth;        // 생년월일 추가

  public OrderItemResponseDto(OrderItem orderItem, OrderResponseDto orderResponseDto) {
    this.orderItemPrice = orderItem.getOrderItemPrice();
    this.orderItemQuantity = orderItem.getOrderItemQuantity();
    this.orderItemStatus = orderItem.getOrderItemStatus();
    this.orderId = orderResponseDto.getOrderId();
    this.itemId = orderItem.getItem().getItemId();
    this.storeName = orderItem.getStore().getStoreName();

    // 카드 정보 설정 (OrderItem 엔티티에서 가져오도록 수정)
    this.cardNumber = orderItem.getCardNumber();
    this.expiry = orderItem.getExpiry();
    this.birth = orderItem.getBirth();
  }
}

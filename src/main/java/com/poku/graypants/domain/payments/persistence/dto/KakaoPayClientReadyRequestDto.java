package com.poku.graypants.domain.payments.persistence.dto;

import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class KakaoPayClientReadyRequestDto {

    private String orderAddr;
    private String orderPhone;
    private List<OrderItem> orderItems;
    private Long userId;
    private Integer totalAmount;
    private String itemName;
    private int quantity;


    @Builder
    public KakaoPayClientReadyRequestDto(String orderAddr, String orderPhone, List<OrderItem> orderItems, Long userId, Integer totalAmount, String itemName, int quantity) {
        this.orderAddr = orderAddr;
        this.orderPhone = orderPhone;
        this.orderItems = orderItems;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}

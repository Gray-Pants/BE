package com.poku.graypants.domain.payments.persistence;

import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class OrderInfoRedis {

    private String orderAddr;
    private String orderPhone;
    private List<OrderItem> orderItems;
    private Long userId;
    private Integer totalAmount;

    @Builder
    public OrderInfoRedis(String orderAddr, String orderPhone, List<OrderItem> orderItems, Long userId, Integer totalAmount) {
        this.orderAddr = orderAddr;
        this.orderPhone = orderPhone;
        this.orderItems = orderItems;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }
}

package com.poku.graypants.domain.orderItem.application.dto;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {

    private Long orderItemId;
    private int orderItemPrice;
    private int orderItemQuantity;
    private OrderItemStatus orderItemStatus;
    private ItemResponseDto item;
    private Long orderId;
    private Long itemId;
    private String storeName;

    public OrderItemResponseDto(OrderItem orderItem) {
        this.orderItemId = orderItem.getOrderItemId();
        this.orderItemPrice = orderItem.getOrderItemPrice();
        this.orderItemQuantity = orderItem.getOrderItemQuantity();
        this.orderItemStatus = orderItem.getOrderItemStatus();
        this.orderId = orderItem.getOrder().getOrderId();
        this.itemId = orderItem.getItem().getItemId();
        this.storeName = orderItem.getStore().getStoreName();
        this.item = new ItemResponseDto(orderItem.getItem());
    }
}

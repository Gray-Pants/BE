package com.poku.graypants.domain.orderItem.application.dto;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.persistence.OrderItemStatus;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {

    private int orderItemPrice;
    private int orderItemQuantity;
    private OrderItemStatus orderItemStatus;
    private ItemResponseDto item;
    private Long orderId;
    private Long itemId;
    private String storeName;

    public OrderItemResponseDto(OrderItem orderItem, OrderResponseDto orderResponseDto) {
        this.orderItemPrice = orderItem.getOrderItemPrice();
        this.orderItemQuantity = orderItem.getOrderItemQuantity();
        this.orderItemStatus = orderItem.getOrderItemStatus();
        this.orderId = orderResponseDto.getOrderId();
        this.itemId = orderItem.getItem().getItemId();
        this.storeName = orderItem.getStore().getStoreName();
    }

    public OrderItemResponseDto(OrderItem orderItem) {
        this.orderItemPrice = orderItem.getOrderItemPrice();
        this.orderItemQuantity = orderItem.getOrderItemQuantity();
        this.orderItemStatus = orderItem.getOrderItemStatus();
        this.orderId = orderItem.getOrder().getOrderId();
        this.itemId = orderItem.getItem().getItemId();
        this.storeName = orderItem.getStore().getStoreName();
        this.item = new ItemResponseDto(orderItem.getItem());
    }
}

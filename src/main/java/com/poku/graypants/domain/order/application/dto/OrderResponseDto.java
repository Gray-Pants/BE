package com.poku.graypants.domain.order.application.dto;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private final Long orderId;
    private final String orderAddr;
    private final String orderPhone;
    private final String userName;
    private final List<OrderItemResponseDto> orderItems;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;
    private final String tid;

    @Builder
    public OrderResponseDto(Order order) {
        this.tid = order.getTid();
        this.orderId = order.getOrderId();
        this.orderAddr = order.getOrderAddr();
        this.orderPhone = order.getOrderPhone();
        this.userName = order.getUser().getUsername();
        this.orderItems = order.getOrderItems().stream().map(OrderItemResponseDto::new).toList();
        this.created_at = order.getCreatedAt();
        this.updated_at = order.getUpdatedAt();
    }
}

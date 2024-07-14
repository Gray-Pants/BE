package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemCreateRequestDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemUpdateRequestDto;

import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto createOrderItem(OrderCreateRequestDto orderCreateRequestDto, Long userId, OrderItemCreateRequestDto orderItemCreateRequestDto, Long itemId);
    OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto);
    List<OrderItemResponseDto> getOrderItemsByOrder(Order order);
    void deleteOrderItem(Long orderItemId);

    OrderItem getVerifyOrderItemByOrderItemId(Long orderItemId);

    OrderItemResponseDto getVerifyOrderItemByOrderItemIdWithUserId(Long orderItemId, Long userId);
}

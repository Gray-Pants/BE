package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderItemCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderItemUpdateRequestDto;
import com.poku.graypants.domain.order.persistence.Order;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto createOrderItem(OrderCreateRequestDto orderCreateRequestDto, OrderItemCreateRequestDto orderItemCreateRequestDto, Long userId, Long itemId);
    List<OrderItemResponseDto> createOrderItems(Order order, Long userId, List<OrderItemCreateRequestDto> orderItemCreateRequestDtos, List<Long> itemIdList);
    OrderItemResponseDto getOrderItem(Long orderItemId, OrderResponseDto orderResponseDto);
    OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto);
    void deleteOrderItem(Long orderItemId);
}

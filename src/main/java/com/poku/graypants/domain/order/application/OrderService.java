package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.domain.order.persistence.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto, Long userId);
    OrderResponseDto getOrder(Long orderId);
    List<OrderResponseDto> getOrders(Long userId);
    OrderResponseDto updateOrder(Long orderId, OrderUpdateRequestDto orderUpdateRequestDto, Long userId);
    void deleteOrder(Long orderId, Long userId);
}

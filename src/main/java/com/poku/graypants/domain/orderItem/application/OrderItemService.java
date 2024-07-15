package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemCreateRequestDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemUpdateRequestDto;

import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto updateOrderItemStatus(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto);
    void deleteOrderItem(Long orderItemId);

    OrderItem getVerifyOrderItemByOrderItemId(Long orderItemId);

    OrderItemResponseDto getVerifyOrderItemByOrderItemIdWithUserId(Long orderItemId, Long userId);
}

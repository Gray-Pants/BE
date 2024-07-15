package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderRepository;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemRepository;
import java.util.Collection;
import java.util.List;

import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemDataService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getReviewRequestsByUserId(Long userId) {
        return orderItemRepository.findAllOrderItemWithNoReviewByUserId(userId);
    }

    public OrderItem createOrderItem(Order order, Item item, int orderItemQuantity, OrderItemStatus orderItemStatus) {
        log.info("item = {}", item.getItemPrice());

        return orderItemRepository.save(OrderItem.builder()
                .order(order)
                .item(item)
                .orderItemPrice(item.getItemPrice())
                .orderItemQuantity(orderItemQuantity)
                .orderItemStatus(orderItemStatus)
                .build());
    }
}

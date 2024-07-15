package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemRepository;
import java.util.List;

import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import com.poku.graypants.domain.store.persistence.Store;
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


    public List<OrderItem> getSortedByDateOrderItemsByStoreId(Long storeId) {
        return orderItemRepository.findByItem_Store_StoreIdOrderByCreatedAtAsc(storeId);
    }

    public OrderItem createOrderItem(Order order, Item item, int orderItemQuantity, Store store, OrderItemStatus orderItemStatus) {

        return orderItemRepository.save(OrderItem.builder()
                .store(store)
                .order(order)
                .item(item)
                .orderItemPrice(item.getItemPrice())
                .orderItemQuantity(orderItemQuantity)
                .orderItemStatus(orderItemStatus)
                .build());
    }
}

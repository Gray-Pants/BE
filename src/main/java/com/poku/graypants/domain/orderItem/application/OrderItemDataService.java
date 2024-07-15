package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.order.persistence.OrderRepository;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

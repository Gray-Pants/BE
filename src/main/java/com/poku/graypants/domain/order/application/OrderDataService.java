package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDataService {

    private final OrderRepository orderRepository;

    public Long countByUser_UserId(Long userId) {
        return orderRepository.countByUser_UserId(userId);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUser_UserId(userId);
    }
}

package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDataService {

    private final OrderRepository orderRepository;

    public Long countByUser_UserId(Long userId) {
        return orderRepository.countByUser_UserId(userId);
    }
}

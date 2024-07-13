package com.poku.graypants.domain.orderItem.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderItemId(Long orderItemId);
}

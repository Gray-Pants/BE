package com.poku.graypants.domain.orderItem.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderItemId(Long orderItemId);

    @Query("SELECT oi FROM OrderItem oi JOIN oi.order o WHERE o.userId = :userId AND oi.review IS NULL")
    List<OrderItem> findAllOrderItemWithNoReviewByUserId(@Param("userId") Long userId);
}

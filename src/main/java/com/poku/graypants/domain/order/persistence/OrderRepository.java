package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.user.persistence.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    Long countByUser_UserId(Long userId);

    List<Order> findAllByUser_UserId(Long userId);
}
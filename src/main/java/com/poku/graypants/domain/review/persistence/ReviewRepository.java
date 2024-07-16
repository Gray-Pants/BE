package com.poku.graypants.domain.review.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItem_ItemId(Long itemId);
    List<Review> findByUser_UserId(Long userId);

    Long countByUser_UserId(Long userId);
}

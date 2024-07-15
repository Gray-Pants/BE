package com.poku.graypants.domain.like.persistence;


import com.poku.graypants.domain.user.persistence.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);
    List<Like> findByUser_UserId(Long userId);

    Long countByItem_ItemId(Long itemId);

    Optional<Like> findByUser_UserIdAndItem_ItemId(Long userId, Long itemId);

    Boolean existsByUser_UserIdAndItem_ItemId(Long userId, Long itemId);
}

package com.poku.graypants.domain.like.persistence;


import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser(User user);
}

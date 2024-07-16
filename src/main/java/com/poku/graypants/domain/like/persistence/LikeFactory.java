package com.poku.graypants.domain.like.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import org.springframework.stereotype.Component;

@Component
public class LikeFactory {

    public Like createLike(User user, Item item) {
        return Like.builder()
                .user(user)
                .item(item)
                .build();
    }
}
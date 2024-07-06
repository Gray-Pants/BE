package com.poku.graypants.domain.like.application.dto;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.user.persistence.User;

public class LikecreateRequestDto {

    private User user;
    private Item item;

    public Like toEntity() {
        return Like.builder()
                .user(user)
                .item(item)
                .build();
    }
}

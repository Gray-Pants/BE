package com.poku.graypants.domain.like.application.dto;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.user.persistence.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeResponseDto {

    private final Long likeId;
    private final User user;
    private final Item item;
    private final LocalDateTime created_at;
    private final LocalDateTime updated_at;

    @Builder
    @QueryProjection
    public LikeResponseDto(Like like) {
        this.likeId = like.getLikeId();
        this.user = like.getUser();
        this.item = like.getItem();
        this.created_at = like.getCreatedAt();
        this.updated_at = like.getUpdatedAt();
    }
}

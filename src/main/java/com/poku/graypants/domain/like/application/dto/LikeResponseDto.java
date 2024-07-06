package com.poku.graypants.domain.like.application.dto;

import com.poku.graypants.domain.like.persistence.Like;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class LikeResponseDto {
    private final Long likeId;
    private final Long userId;
    private final Long itemId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    @QueryProjection
    public LikeResponseDto(Like like) {
        this.likeId = like.getLikeId();
        this.createdAt = like.getCreatedAt();
        this.updatedAt = like.getUpdatedAt();
        this.userId = like.getUser().getId();
        this.itemId = like.getItem().getId();
    }
}

package com.poku.graypants.domain.like.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeResponseDto {
    private Long likeId;
    private Long userId;
    private Long itemId;

    @Builder
    public LikeResponseDto(Long likeId, Long userId, Long itemId) {
        this.likeId = likeId;
        this.userId = userId;
        this.itemId = itemId;
    }
}

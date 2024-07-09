package com.poku.graypants.domain.like.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeRequestDto {
    private Long userId;
    private Long itemId;

    @Builder
    public LikeRequestDto(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }
}

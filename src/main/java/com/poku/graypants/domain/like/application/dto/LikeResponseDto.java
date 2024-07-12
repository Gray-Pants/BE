package com.poku.graypants.domain.like.application.dto;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
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
    private ItemResponseDto item;

    @Builder
    public LikeResponseDto(Long likeId, Long userId, ItemResponseDto item) {
        this.likeId = likeId;
        this.userId = userId;
        this.item = item;
    }
}

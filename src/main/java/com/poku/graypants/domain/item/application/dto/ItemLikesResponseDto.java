package com.poku.graypants.domain.item.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class ItemLikesResponseDto {
    private Long itemId;
    private Long likesCount;
}

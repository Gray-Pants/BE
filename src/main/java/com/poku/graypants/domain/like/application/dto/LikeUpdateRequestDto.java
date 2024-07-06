package com.poku.graypants.domain.like.application.dto;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LikeUpdateRequestDto {

    private final Long id;
    private final User user;
    private final Item item;
}

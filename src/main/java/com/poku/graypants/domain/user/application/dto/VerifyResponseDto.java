package com.poku.graypants.domain.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class VerifyResponseDto {
    private final String message;
}

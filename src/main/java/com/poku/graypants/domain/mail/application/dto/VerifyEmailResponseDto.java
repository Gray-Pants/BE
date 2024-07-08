package com.poku.graypants.domain.mail.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class VerifyEmailResponseDto {
    private String verifiedCode;
}

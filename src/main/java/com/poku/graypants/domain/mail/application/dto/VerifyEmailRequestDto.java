package com.poku.graypants.domain.mail.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyEmailRequestDto {
    private String email;
    private String authCode;
}

package com.poku.graypants.domain.mail.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class VerificationEmailSendRequestDto {
    private String email;
}

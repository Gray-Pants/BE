package com.poku.graypants.domain.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailSignupRequestDto {
    private String name;
    private String email;
    private String password;
    private String verificationCode;
    private String role;
}

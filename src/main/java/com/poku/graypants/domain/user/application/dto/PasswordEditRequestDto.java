package com.poku.graypants.domain.user.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEditRequestDto {
    private String password;
    private String newPassword;
    private String confirmPassword;
}

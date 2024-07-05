package com.poku.graypants.domain.auth.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailLoginRequestDto {
    private String email;
    private String password;

}

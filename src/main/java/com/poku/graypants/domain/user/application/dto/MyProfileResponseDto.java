package com.poku.graypants.domain.user.application.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MyProfileResponseDto {
    private String username;
    private Long orderCount;
    private Long reviewCount;
    private String grade;
}

package com.poku.graypants.domain.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberInformationResponseDto {
    private String userId;
    private String username;
    private String Number;
    private String address;
}

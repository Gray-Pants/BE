package com.poku.graypants.domain.addr.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddrRequestDto {
    private String userAddrName;
    private String userAddr;
    private Long userId;
    private String userAddrPhone;

}

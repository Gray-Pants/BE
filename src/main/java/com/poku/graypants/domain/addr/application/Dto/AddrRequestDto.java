package com.poku.graypants.domain.addr.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddrRequestDto {
    private Long userAddrId;
    private String userAddrName;
    private String userAddr;
    private Long userId;
    private String userAddrPhone;

}

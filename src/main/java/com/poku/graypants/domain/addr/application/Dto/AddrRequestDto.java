package com.poku.graypants.domain.addr.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class AddrRequestDto {
    private String userAddrName;
    private String userAddr;
    @Setter
    private Long userId;
    private String userAddrPhone;

}

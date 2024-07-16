package com.poku.graypants.domain.addr.application.Dto;

import com.poku.graypants.domain.addr.persistence.UserAddr;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddrResponseDto {
    private Long userAddrId;
    private String userAddr;
    private String userAddrPhone;
    private String userAddrName;

    public static AddrResponseDto toDto(UserAddr userAddr) {
        return new AddrResponseDto(
                userAddr.getUserAddrId(),
                userAddr.getUserAddr(),
                userAddr.getUserAddrPhone(),
                userAddr.getUserAddrName()
        );
    }

}
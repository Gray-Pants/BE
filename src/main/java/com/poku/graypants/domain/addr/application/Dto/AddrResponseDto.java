package com.poku.graypants.domain.addr.application.Dto;

import com.poku.graypants.domain.addr.persistence.UserAddr;
import com.poku.graypants.domain.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddrResponseDto {
    private Long userAddrId;
    private Long userId;
    private String userAddr;
    private String userAddrPhone;
    private String userAddrName;

    public static AddrResponseDto fromEntity(UserAddr userAddr) {
        return new AddrResponseDto(
                userAddr.getUserAddrId(),
                userAddr.getUser().getUserId(),
                userAddr.getUserAddr(),
                userAddr.getUserAddrPhone(),
                userAddr.getUserAddrName()
        );
    }

}
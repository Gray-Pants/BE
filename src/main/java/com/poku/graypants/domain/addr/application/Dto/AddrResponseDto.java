package com.poku.graypants.domain.addr.application.Dto;

import com.poku.graypants.domain.addr.persistence.UserAddr;
import com.poku.graypants.domain.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddrResponseDto {
    private Long userAddrId;
    private Long userId;
    private String userAddr;
    private String userAddrPhone;
    private String userAddrName;

//    public static AddrResponseDto toDto(UserAddr userAddr) {
//        return new AddrResponseDto(
//                userAddr.getUserAddrId(),
//                userAddr.getUser().getUserId(),
//                userAddr.getUserAddr(),
//                userAddr.getUserAddrPhone(),
//                userAddr.getUserAddrName()
//        );
//    }
//    // UserAddr 객체를 AddrResponseDto로 변환하는 메서드
    public static AddrResponseDto toDto(UserAddr userAddr) {
        return AddrResponseDto.builder()
                .userAddrId(userAddr.getUserAddrId())
                .userId(userAddr.getUser().getUserId())
                .userAddr(userAddr.getUserAddr())
                .userAddrPhone(userAddr.getUserAddrPhone())
                .userAddrName(userAddr.getUserAddrName())
                .build();
    }

}

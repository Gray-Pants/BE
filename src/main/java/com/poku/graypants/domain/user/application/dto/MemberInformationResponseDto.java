package com.poku.graypants.domain.user.application.dto;

import com.poku.graypants.domain.addr.application.Dto.AddrResponseDto;
import com.poku.graypants.domain.user.persistence.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberInformationResponseDto {
    private String username;
    private String email;
    private List<AddrResponseDto> addrs;

    public MemberInformationResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.addrs = user.getUserAddrs().stream().map(AddrResponseDto::toDto).toList();
    }
}

package com.poku.graypants.domain.payments.persistence;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KakaoPayApproveResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;

    private LocalDateTime created_at;
}

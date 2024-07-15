package com.poku.graypants.domain.payments.persistence.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KakaoPayApproveResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private Integer amount;
    private String itemName;
    private String paymentMethodType;

    private LocalDateTime created_at;
}

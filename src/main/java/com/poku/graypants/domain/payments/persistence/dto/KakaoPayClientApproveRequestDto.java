package com.poku.graypants.domain.payments.persistence.dto;

import lombok.Getter;

@Getter
public class KakaoPayClientApproveRequestDto {

    private String pgToken;
    private String tid;
}

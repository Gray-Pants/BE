package com.poku.graypants.domain.payments.persistence;

import lombok.Getter;

@Getter
public class KakaoPayClientApproveRequestDto {

    private String pgToken;
    private String tid;
}

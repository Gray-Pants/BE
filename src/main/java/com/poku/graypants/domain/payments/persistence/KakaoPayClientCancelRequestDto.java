package com.poku.graypants.domain.payments.persistence;

import lombok.Getter;

@Getter
public class KakaoPayClientCancelRequestDto {

    //결제 고유 번호, 20자
    //private String tid;
    private int cancelAmount;

    public KakaoPayClientCancelRequestDto(int cancelAmount) {
        this.cancelAmount = cancelAmount;
    }
}

package com.poku.graypants.domain.payments.persistence;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoPayCancelRequestDto {

    private String cid;
    //결제 고유 번호, 20자
    private String tid;
    private int cancelAmount;
    private int cancelTaxFreeAmount;

    @Builder
    public KakaoPayCancelRequestDto(String cid, String tid, int cancelAmount, int cancelTaxFreeAmount) {
        this.cid = cid;
        this.tid = tid;
        this.cancelAmount = cancelAmount;
        this.cancelTaxFreeAmount = cancelTaxFreeAmount;
    }
}

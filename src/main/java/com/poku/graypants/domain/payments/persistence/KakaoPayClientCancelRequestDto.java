package com.poku.graypants.domain.payments.persistence;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoPayClientCancelRequestDto {

    //결제 고유 번호, 20자
    private Long orderId;
    private int cancelAmount;

    @Builder
    public KakaoPayClientCancelRequestDto(Long orderId, int cancelAmount) {
        this.orderId = orderId;
        this.cancelAmount = cancelAmount;
    }
}

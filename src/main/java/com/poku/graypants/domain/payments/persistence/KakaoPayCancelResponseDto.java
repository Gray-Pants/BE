package com.poku.graypants.domain.payments.persistence;

import lombok.Getter;

@Getter
public class KakaoPayCancelResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
}

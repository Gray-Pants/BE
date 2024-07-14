package com.poku.graypants.domain.payments.persistence;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoPayClientReadyRequestDto {

    private String itemName;
    private int quantity;
    private int totalAmount;


    @Builder
    public KakaoPayClientReadyRequestDto(String itemName, int quantity, int totalAmount) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
}

package com.poku.graypants.domain.payments.persistence;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayReadyRequestDto {

    //가맹점 코드, 10자
    private String cid;

    //가맹점 주문번호, 최대 100자
    private String partnerOrderId;

    //가맹점 회원 id, 최대 100자
    private String partnerUserId;

    private String itemName;
    private int quantity;
    private int totalAmount;
    private int taxFreeAmount;

    //결제 성공 시 redirect url, 최대 255자
    private String approvalUrl;
    //결제 취소 시 redirect url, 최대 255자
    private String cancelUrl;
    //결제 실패 시 redirect url, 최대 255자
    private String failUrl;

    @Builder
    public KakaoPayReadyRequestDto(String cid, String partnerOrderId, String partnerUserId, String itemName, int quantity, int totalAmount, int taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl) {
        this.cid = cid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.taxFreeAmount = taxFreeAmount;
        this.approvalUrl = approvalUrl;
        this.cancelUrl = cancelUrl;
        this.failUrl = failUrl;
    }
}

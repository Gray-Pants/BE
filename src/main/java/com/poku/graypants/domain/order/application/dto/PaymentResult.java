package com.poku.graypants.domain.order.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.siot.IamportRestClient.response.Payment;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResult {
  private boolean success;
  @JsonProperty("imp_uid")
  private String impUid;
  @JsonProperty("merchant_uid")
  private String merchantUid;
  @JsonProperty("pay_method")
  private String payMethod;
  private BigDecimal amount;
  private String status;
  @JsonProperty("paid_at")
  private LocalDateTime paidAt; // Unix timestamp
  @JsonProperty("receipt_url")
  private String receiptUrl;
  @JsonProperty("apply_num")
  private String applyNum;
  @JsonProperty("error_code")
  private String errorCode;
  @JsonProperty("error_msg")
  private String errorMsg;

  public PaymentResult(Payment payment) {
    this.success = payment.getStatus().equals("paid");
    this.impUid = payment.getImpUid();
    this.merchantUid = payment.getMerchantUid();
    this.payMethod = payment.getPayMethod();
    this.amount = payment.getAmount();
    this.status = payment.getStatus();
    this.paidAt = LocalDateTime.now();
    this.receiptUrl = payment.getReceiptUrl();
    this.applyNum = payment.getApplyNum();

  }
}

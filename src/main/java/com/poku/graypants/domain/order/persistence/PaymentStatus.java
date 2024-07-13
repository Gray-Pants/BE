package com.poku.graypants.domain.order.persistence;

public enum PaymentStatus {
  READY(0, "결제 대기"),
  PAID(1, "결제 완료"),
  FAILED(2, "결제 실패"),
  CANCELLED(3, "결제 취소");

  private final int payStatusCode;
  private final String message;

  PaymentStatus(int payStatusCode, String message) {
    this.payStatusCode = payStatusCode;
    this.message = message;
  }
}

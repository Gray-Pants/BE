package com.poku.graypants.domain.order.persistence;

import lombok.Getter;

@Getter
public enum OrderStatus {
    COMPLETE(0, "주문 완료"),
    FAILED(1, "주문 실패"),
    CANCELLED(2, "주문 취소");

    OrderStatus(int orderStatusCode, String message) {

    }
}

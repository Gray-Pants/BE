package com.poku.graypants.domain.orderItem.persistence;

import lombok.Getter;

@Getter
public enum OrderItemStatus {
    COMPLETE(0, "주문 완료"),
    FAILED(1, "주문 실패"),
    CANCELLED(2, "주문 취소");

    OrderItemStatus(int orderItemStatus, String message) {

    }
}

package com.poku.graypants.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Item Not Found"),
    Like_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Like Not Found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Category Not Found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404,"User not found"),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 수량입니다." );

    private final int status;
    private final int customCode;
    private final String message;
    private final String err;

    ExceptionStatus(HttpStatus httpStatus, int customCode, String message) {
        this.status = httpStatus.value();
        this.customCode = customCode;
        this.message = message;
        this.err = httpStatus.getReasonPhrase();
    }
}

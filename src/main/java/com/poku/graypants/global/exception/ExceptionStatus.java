package com.poku.graypants.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

    RANDOM_NUMBER_GENERATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Random Number Generate Failed"),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Item Not Found"),
    Like_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Like Not Found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "User not found"),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 수량입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Category Not Found"),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 메일 인증입니다."),
    INVALID_PASSWORD(HttpStatus.FORBIDDEN, 403, "Invalid Password"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, 403, "권한이 없습니다.");

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

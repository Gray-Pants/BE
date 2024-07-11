package com.poku.graypants.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

    // COMMON
    RANDOM_NUMBER_GENERATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Random Number Generate Failed"),

    // ITEM
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 상품입니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "찜한 상품이 없습니다."),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 수량입니다." ),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404,"유저를 조회할 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.FORBIDDEN, 400, "허용되지 않는 비밀번호입니다."),
    INVALID_ROLE(HttpStatus.BAD_REQUEST, 400, "허용되지 않은 역할입니다."),

    // MAIL
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 메일 인증입니다."),

    // CATEGORY
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 카테고리입니다."),
    DUPLICATED_EMAIL(HttpStatus.FORBIDDEN, 403, "이미 존재하는 이메일입니다."),

    // STORE
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 스토어입니다."),

    // ORDER
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "주문을 조회할 수 없습니다."),
    ORDER_AND_USER_MISMATCH(HttpStatus.BAD_REQUEST, 400, "주문정보와 유저정보가 일치할 수 없습니다."),

    // ORDER_ITEM
    ORDER_ITEM_INFO_AND_STORE_INFO_MISMATCH(HttpStatus.BAD_REQUEST, 400, "상품 정보와 판매자 정보가 일치하지 않습니다."),
    ORDER_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "주문 상품을 조회할 수 없습니다."),

    // IMAGE
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "파일을 찾을 수 없습니다.");



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

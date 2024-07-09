package com.poku.graypants.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.File;

@Getter
public enum ExceptionStatus {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Item Not Found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "Category Not Found"),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "ItemImgFile Not Found");

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

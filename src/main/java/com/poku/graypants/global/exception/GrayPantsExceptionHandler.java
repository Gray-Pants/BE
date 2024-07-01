package com.poku.graypants.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GrayPantsExceptionHandler {
    @ExceptionHandler(GrayPantsException.class)
    public ResponseEntity<Object> handleVolumeTestException(GrayPantsException e) {
        //로그 로직 필요
        log.info("예외 발생 : {}", e.getMessage());
//        return new ResponseEntity(error(e.getMessage()), new HttpHeaders(), e.getStatus());
        return null;
    }
}

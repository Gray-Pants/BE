package com.poku.graypants.global.exception;

public class GrayPantsException extends RuntimeException {

        private final ExceptionStatus exceptionStatus;

        public GrayPantsException(ExceptionStatus exceptionStatus) {
            this.exceptionStatus = exceptionStatus;
        }

        public ExceptionStatus getExceptionCode() {
            return exceptionStatus;
        }

        @Override
        public String getMessage() {
            return exceptionStatus.getMessage();
        }

        public int getStatus() {
            return exceptionStatus.getStatus();
        }
}

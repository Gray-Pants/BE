package com.poku.graypants.global.util;

import java.util.List;
import java.util.stream.Collectors;

public class ApiResponseUtil {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response);
    }

    public static <T> List<ApiResult<T>> success(List<T> response) {
        return response.stream()
                .map(value -> new ApiResult<>(true, value))
                .collect(Collectors.toList());
    }

    public static ApiResult<ApiError> error(Throwable throwable) {
        return new ApiResult<>(false, new ApiError(throwable));
    }

    public static ApiResult<ApiError> error(String message) {
        return new ApiResult<>(false, new ApiError(message));
    }

    public static class ApiError {
        private final String message;

        ApiError(Throwable throwable) {
            this(throwable.getMessage());
        }

        ApiError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ApiResult<T> {
        private final boolean success;
        private final T response;

        public ApiResult(boolean success, T response) {
            this.success = success;
            this.response = response;
        }

        public boolean isSuccess() {
            return success;
        }

        public T getResponse() {
            return response;
        }
    }
}

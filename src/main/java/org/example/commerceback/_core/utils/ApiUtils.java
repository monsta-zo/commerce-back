package org.example.commerceback._core.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(String message, T result) {
        return new ApiResult<>(message, result);
    }

    public static ApiResult<?> error(String message) {
        return new ApiResult<>(message, null);
    }

    @Getter @Setter @AllArgsConstructor
    public static class ApiResult<T> {
        private final String message;
        private final T data;
    }
}

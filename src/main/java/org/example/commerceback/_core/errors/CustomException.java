package org.example.commerceback._core.errors;

import org.example.commerceback._core.utils.ApiUtils;
import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException {
    private final ExceptionCode exceptionCode;
    private final String message;

    public CustomException(ExceptionCode exceptionCode){
        this.exceptionCode = exceptionCode;
        this.message = exceptionCode.getMessage();
    }

    public CustomException(ExceptionCode exceptionCode, String message) {
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

    public ApiUtils.ApiResult<?> body() {
        return ApiUtils.error(message);
    }

    public HttpStatus status(){
        return exceptionCode.getHttpStatus();
    }
}

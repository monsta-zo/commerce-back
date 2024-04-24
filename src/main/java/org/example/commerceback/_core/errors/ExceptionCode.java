package org.example.commerceback._core.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // User 관련
    USER_PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    USER_EMAIL_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    USER_NO_EXIST(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "올바른 형식을 입력해주세요."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 상품을 찾을 수 없습니다."),
    ORDER_PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 주문 상품을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

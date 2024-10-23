package com.midasdev.mochat.global.exception;

import java.text.MessageFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionType {
    // token
    /**
     * 토큰 타입이 맞지 않을 때 <br>
     * - {0} : 전달된 토큰 타입<br>
     * - {1} : 필요한 토큰 타입
     */
    TOKEN_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "ERR_TOKEN_001", "토큰 타입이 맞지 않습니다. (전달된 토큰 : {0}, 필요한 토큰 {1})"),

    // global
    BEFORE_CONTROLLER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "ERR_GLOBAL_001", "API 로직 수행 전 에러 서버 에러 발생 : {0}"),
    UNDEFINED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "ERR_GLOBAL_999", "정의되지 않은 에러입니다. : {0}");

    private final HttpStatus httpStatus;
    private final String exceptionCode;
    private final String errorMessage;

    public String getErrorMessage(Object... args) {
        return MessageFormat.format(errorMessage, args);
    }
}

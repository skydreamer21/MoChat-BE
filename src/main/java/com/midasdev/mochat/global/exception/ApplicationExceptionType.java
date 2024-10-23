package com.midasdev.mochat.global.exception;

import java.text.MessageFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionType {

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

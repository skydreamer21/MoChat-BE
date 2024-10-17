package com.midasdev.mochat.global.response;

import com.midasdev.mochat.global.exception.ApplicationException;
import com.midasdev.mochat.global.exception.ApplicationExceptionType;
import lombok.Builder;

@Builder
public record ExceptionResponse(String httpStatus, String errorCode, String message) {

    public static ExceptionResponse from(ApplicationException exception) {
        ApplicationExceptionType exceptionType = exception.getExceptionType();
        return ExceptionResponse.builder()
                                .httpStatus(exceptionType.getHttpStatus().toString())
                                .errorCode(exceptionType.getExceptionCode())
                                .message(exception.getMessage())
                                .build();
    }

}

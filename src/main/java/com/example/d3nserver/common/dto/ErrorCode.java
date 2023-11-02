package com.example.d3nserver.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /**
     * 2XXX -> JWT 에러
     */
    JWT_EMPTY(2000, "Jwt 토큰이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(2001, "Jwt 토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_JWT_TOKEN(2002, "유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN(2003, "유효하지 않은 리프레시 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INCORRECT_REFRESH_TOKEN(2004, "유저의 리프레시 토큰과 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

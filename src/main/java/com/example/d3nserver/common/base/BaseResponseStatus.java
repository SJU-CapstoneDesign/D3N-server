package com.example.d3nserver.common.base;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 2XXX -> JWT 에러
     */
    JWT_EMPTY(2000, "Jwt 토큰이 존재하지 않습니다."),
    JWT_EXPIRED(2001, "Jwt 토큰이 만료되었습니다."),
    INVALID_JWT_TOKEN(2002, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(2003, "유효하지 않은 리프레시 토큰입니다."),
    INCORRECT_REFRESH_TOKEN(2004, "유저의 리프레시 토큰과 일치하지 않습니다.");

    private final int code;
    private final String message;

    BaseResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

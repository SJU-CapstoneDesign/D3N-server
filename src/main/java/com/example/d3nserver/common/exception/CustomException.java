package com.example.d3nserver.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends Exception{
    private ErrorCode errorCode;
}

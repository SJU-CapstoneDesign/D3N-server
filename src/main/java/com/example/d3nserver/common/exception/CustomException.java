package com.example.d3nserver.common.exception;

import com.example.d3nserver.common.dto.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends Exception{
    private ErrorCode errorCode;
}

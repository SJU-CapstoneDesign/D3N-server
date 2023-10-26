package com.example.d3nserver.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseResponseStatus status;
}

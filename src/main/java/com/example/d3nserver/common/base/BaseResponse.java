package com.example.d3nserver.common.base;

import lombok.Getter;

@Getter
public class BaseResponse<T> {
    private final Boolean success;
    private final T result;
    private final BaseExceptionResponse error;

    public BaseResponse(T result) {
        this.success = true;
        this.result = result;
        this.error = null;
    }
    public static<T> BaseResponse<T> ofSuccess(T result){
        return new BaseResponse<>(result);
    }
}

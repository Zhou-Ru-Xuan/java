package com.example.springlearning.result;

import com.example.springlearning.enums.Enums;
import lombok.Data;

@Data
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(Enums enums, T data) {
        this.code = enums.getCode();
        this.message = enums.getDesc();
        this.data = data;
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


}

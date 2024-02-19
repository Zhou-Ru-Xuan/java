package com.example.springlearning.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;

    public ApiResult(String message) {
        this.message = message;
    }

    public ApiResult(int code, T data) {
        this.code = code;
        this.data = data;
    }
}

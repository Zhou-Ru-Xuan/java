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

    public static ApiResult buildSuccess() {
        return new ApiResult<>(200, null, true);
    }

    public static ApiResult buildError(String message) {
        return new ApiResult<>(500, message, false);
    }
}

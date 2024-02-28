package com.example.springlearning.enums;

import lombok.Getter;

@Getter
public enum Enums {
    SUCCESS(200, "success"),
    FAIL(500, "fail");
    private Integer code;
    private String desc;

    Enums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}

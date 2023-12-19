package com.zhouruxuan.enum2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SimpleEnum {
    VALUE1(1 << 1),
    VALUE2(1 << 2),
    VALUE3(1 << 3),
    VALUE11(1 << 11),
    VALUE12(1 << 12),
    VALUE13(1 << 13),

    ;

    private final int value;
}

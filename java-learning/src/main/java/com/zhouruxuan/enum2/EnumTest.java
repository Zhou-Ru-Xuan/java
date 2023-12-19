package com.zhouruxuan.enum2;

import org.junit.jupiter.api.Test;

public class EnumTest {
    @Test
    public void testEnum(){
        SimpleEnum[] values = SimpleEnum.values();
        for (SimpleEnum value : values) {
            System.out.println(value.getValue());
        }

        System.out.println(SimpleEnum.VALUE13.getValue());
    }
}

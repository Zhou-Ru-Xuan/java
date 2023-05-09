package com.zhouruxuan.lombok;

import org.junit.Test;

public class LombokTest {
    @Test
    public void testBuilder(){
        LombokEntity build = LombokEntity.builder().build();
    }

}

package com.zhouruxuan.grammer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitOperationTest {
    @Test
    public void testOr() {
        int i = 1 << 4;
        int j = 1 << 10;
        int result = i | j;
        result &= ~i;
        Assertions.assertEquals(result, j);
    }
}

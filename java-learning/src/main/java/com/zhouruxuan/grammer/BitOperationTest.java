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

        Assertions.assertEquals(40,reverseOr(40, 16));
        Assertions.assertEquals(32,reverseOr(40, 24));
        Assertions.assertEquals(40,reverseOr(40, 3));
        Assertions.assertEquals(40,reverseOr(40, 6));

    }

    public int reverseOr(int sum, Integer... nums) {
        for (Integer num : nums) {
            sum &= ~num;
        }
        return sum;
    }
}

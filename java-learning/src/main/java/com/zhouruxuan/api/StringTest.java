package com.zhouruxuan.api;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {
    @Test
    public void format_test() {
        Assert.assertEquals("1day", String.format("%sday", 1));
        Assert.assertEquals("1%day", String.format("%s%%day", 1));
    }
}

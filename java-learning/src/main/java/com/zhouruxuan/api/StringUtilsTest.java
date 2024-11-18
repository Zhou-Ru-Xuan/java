package com.zhouruxuan.api;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void remove_test() {
        Assert.assertEquals("defg", StringUtils.remove("abcdefg", "abc"));

        Assert.assertEquals("abc", StringUtils.remove("abc", "abcdefg"));
    }
}

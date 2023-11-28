package com.zhouruxuan.api.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class DateFormatValidator {
    private static final String DATE_FORMAT_REGEX = "^(\\d{4})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$";

    public static boolean isValidDateFormat(String input) {
        return Pattern.matches(DATE_FORMAT_REGEX, input);
    }

    /**
     * 测试
     */
    @Test
    public void testIntDateFormat() {
        String dateString1 = "20211211";
        String dateString2 = "20211301";
        String dateString3 = "20210229";
        String dateString4 = "20211301";
        Assertions.assertTrue(isValidDateFormat(dateString1));
        Assertions.assertFalse(isValidDateFormat(dateString2));
        Assertions.assertTrue(isValidDateFormat(dateString3));
        Assertions.assertFalse(isValidDateFormat(dateString4));
    }
}

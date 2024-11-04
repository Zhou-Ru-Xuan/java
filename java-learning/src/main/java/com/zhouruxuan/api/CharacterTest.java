package com.zhouruxuan.api;

import org.junit.Assert;
import org.junit.Test;

public class CharacterTest {
    @Test
    public void isLetterOrDigit_test() {
        Assert.assertTrue("13abAB".chars().allMatch(CharacterTest::isChineseCharacter));
        Assert.assertFalse("13abAB.".chars().allMatch(CharacterTest::isChineseCharacter));
        Assert.assertFalse("13abA&.".chars().allMatch(CharacterTest::isChineseCharacter));
        Assert.assertFalse("草1332".chars().allMatch(CharacterTest::isChineseCharacter));
    }

    // 判断字符是否是中文字符
    private static boolean isChineseCharacter(int codePoint) {
        return Character.isLetterOrDigit(codePoint) && Character.UnicodeScript.of(codePoint) != Character.UnicodeScript.HAN;
    }
}

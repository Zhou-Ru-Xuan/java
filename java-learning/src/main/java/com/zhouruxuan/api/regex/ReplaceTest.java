package com.zhouruxuan.api.regex;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTest {
    @Test
    public void testRegexBucket() {
        String origin1 = "这是第{1}次示例，名字是{周汝轩}，剩余的东西不知道{}。";
        String origin2 = "这是第1次示例，名字是周汝轩，剩余的东西不知道。";
        // 把大括号中的元素提取出来
        Assertions.assertEquals(Lists.newArrayList("1", "周汝轩", ""), extractBracketsContent(origin1));
        // 把大括号的元素替换成提供的元素
        Assertions.assertEquals("这是第2次示例，名字是不知道，剩余的东西不知道吗。", replaceBracketsContent(origin1, Lists.newArrayList("2", "不知道", "吗")));
        Assertions.assertEquals(origin2, replaceBracketsContent(origin2, Lists.newArrayList("2", "不知道", "吗")));

    }

    /**
     * 匹配字符串中的{}，并把{}依次替换成入参中的各个变量
     *
     * @param str
     * @param replacements
     * @return
     */
    public static String replaceBracketsContent(String str, List<String> replacements) {
        if (replacements.size() == 0) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        Pattern pattern = Pattern.compile("\\{([^}]*)}");
        Matcher matcher = pattern.matcher(sb);

        while (matcher.find()) {
            if (index < replacements.size()) {
                sb.replace(matcher.start(), matcher.end(), replacements.get(index));
                index++;
                matcher = pattern.matcher(sb);
            } else {
                break;
            }
        }

        return sb.toString();
    }

    /**
     * 把字符串中，{}里面的内容取出来
     *
     * @param str
     * @return
     */
    public static List<String> extractBracketsContent(String str) {
        List<String> contentList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{([^}]*)}");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String content = matcher.group(1);
            contentList.add(content);
        }

        return contentList;
    }
}

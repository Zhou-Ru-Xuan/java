package com.zhouruxuan.structural.adapter.demo3.common;

public class ASensitiveWordsFilter { // A敏感词过滤系统提供的接口
    //text是原始文本，函数输出用***替换敏感词之后的文本
    public String filterSexyWords(String text) {
        // ...
        return this.getClass().getSimpleName() + "#filterSexyWords";
    }

    public String filterPoliticalWords(String text) {
        // ...
        return this.getClass().getSimpleName() + "#filterPoliticalWords";
    }
}
package com.zhouruxuan.structural.adapter.demo3.impl2;

import com.zhouruxuan.structural.adapter.demo3.common.BSensitiveWordsFilter;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-16
 **/
public class BSensitiveWordsFilterAdapter implements ISensitiveWordsFilter{

    private final BSensitiveWordsFilter bSensitiveWordsFilter;

    public BSensitiveWordsFilterAdapter(BSensitiveWordsFilter bSensitiveWordsFilter) {
        this.bSensitiveWordsFilter = bSensitiveWordsFilter;
    }


    @Override
    public String filter(String text) {
        //一些额外的处理
        return bSensitiveWordsFilter.filter(text);
    }
}

package com.zhouruxuan.structural.adapter.demo3.impl2;

import com.zhouruxuan.structural.adapter.demo3.common.BSensitiveWordsFilter;
import com.zhouruxuan.structural.adapter.demo3.common.CSensitiveWordsFilter;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-16
 **/
public class CSensitiveWordsFilterAdapter implements ISensitiveWordsFilter{
    private final CSensitiveWordsFilter cSensitiveWordsFilter;

    public CSensitiveWordsFilterAdapter(CSensitiveWordsFilter cSensitiveWordsFilter) {
        this.cSensitiveWordsFilter = cSensitiveWordsFilter;
    }


    @Override
    public String filter(String text) {
        //一些额外的处理
        return cSensitiveWordsFilter.filter(text,"1");
    }
}

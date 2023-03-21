package com.zhouruxuan.structural.adapter.demo3.impl1;

import com.zhouruxuan.structural.adapter.demo3.common.ASensitiveWordsFilter;
import com.zhouruxuan.structural.adapter.demo3.common.BSensitiveWordsFilter;
import com.zhouruxuan.structural.adapter.demo3.common.CSensitiveWordsFilter;

// 未使用适配器模式之前的代码：代码的可测试性、扩展性不好
public class RiskManagement {
  private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
  private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();
  private CSensitiveWordsFilter cFilter = new CSensitiveWordsFilter();
  
  public String filterSensitiveWords(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    maskedText = bFilter.filter(maskedText);
    maskedText = cFilter.filter(maskedText, "***");
    return maskedText;
  }
}
package com.zhouruxuan.structural.adapter.demo3.impl2;

import com.zhouruxuan.structural.adapter.demo3.common.ASensitiveWordsFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
  private final ASensitiveWordsFilter aFilter;

  public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter) {
    this.aFilter = aFilter;
  }

  public String filter(String text) {
    String maskedText = aFilter.filterSexyWords(text);
    maskedText = aFilter.filterPoliticalWords(maskedText);
    return maskedText;
  }
}
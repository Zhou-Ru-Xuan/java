package com.zhouruxuan.behavioral.chainofresponsibility.demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词过滤器
 */
public class SensitiveWordFilterChain {
  private List <SensitiveWordFilter>filters = new ArrayList<>();

  public void addFilter(SensitiveWordFilter filter) {
    this.filters.add(filter);
  }

  // return true if content doesn't contain sensitive words.
  public boolean filter(Content content) {
    for (SensitiveWordFilter filter : filters) {
      if (!filter.doFilter(content)) {
        return false;
      }
    }
    return true;
  }
}
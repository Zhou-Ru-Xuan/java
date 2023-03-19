package com.zhouruxuan.creational.factory.demo1.common2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;
import com.zhouruxuan.creational.factory.demo1.common1.PropertiesRuleConfigParser;

public class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
  @Override
  public IRuleConfigParser createParser() {
    return new PropertiesRuleConfigParser();
  }
}
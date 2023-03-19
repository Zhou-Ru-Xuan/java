package com.zhouruxuan.creational.factory.demo1.common2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;
import com.zhouruxuan.creational.factory.demo1.common1.YamlRuleConfigParser;

public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
  @Override
  public IRuleConfigParser createParser() {
    return new YamlRuleConfigParser();
  }
}
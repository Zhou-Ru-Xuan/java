package com.zhouruxuan.creational.factory.demo1.common2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;
import com.zhouruxuan.creational.factory.demo1.common1.JsonRuleConfigParser;

public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
  @Override
  public IRuleConfigParser createParser() {
    return new JsonRuleConfigParser();
  }
}
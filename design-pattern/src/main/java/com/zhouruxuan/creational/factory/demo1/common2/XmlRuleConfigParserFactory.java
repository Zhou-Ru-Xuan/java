package com.zhouruxuan.creational.factory.demo1.common2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;
import com.zhouruxuan.creational.factory.demo1.common1.XmlRuleConfigParser;

public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
  @Override
  public IRuleConfigParser createParser() {
    return new XmlRuleConfigParser();
  }
}
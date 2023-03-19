package com.zhouruxuan.creational.abstractfactory.demo1.factory;


import com.zhouruxuan.creational.abstractfactory.demo1.parse.IRuleConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.ISystemConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.XmlRuleConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.XmlSystemConfigParser;

public class XmlConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new XmlRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new XmlSystemConfigParser();
  }
}
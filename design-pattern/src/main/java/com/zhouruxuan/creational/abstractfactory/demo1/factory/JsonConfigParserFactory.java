package com.zhouruxuan.creational.abstractfactory.demo1.factory;


import com.zhouruxuan.creational.abstractfactory.demo1.parse.IRuleConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.ISystemConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.JsonRuleConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.JsonSystemConfigParser;

public class JsonConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new JsonRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new JsonSystemConfigParser();
  }
}


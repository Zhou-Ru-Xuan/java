package com.zhouruxuan.creational.factory.demo1.common2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;

public interface IRuleConfigParserFactory {
  IRuleConfigParser createParser();
}
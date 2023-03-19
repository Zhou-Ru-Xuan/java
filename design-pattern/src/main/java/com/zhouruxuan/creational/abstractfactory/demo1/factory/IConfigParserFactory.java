package com.zhouruxuan.creational.abstractfactory.demo1.factory;

import com.zhouruxuan.creational.abstractfactory.demo1.parse.IRuleConfigParser;
import com.zhouruxuan.creational.abstractfactory.demo1.parse.ISystemConfigParser;
public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser();
    //此处可以扩展新的parser类型，比如IBizConfigParser
}
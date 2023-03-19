package com.zhouruxuan.creational.abstractfactory.demo1.parse;

import com.zhouruxuan.creational.abstractfactory.demo1.RuleConfig;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-15
 **/
public class JsonSystemConfigParser implements ISystemConfigParser {
    @Override
    public RuleConfig parse(String configText) {
        return new RuleConfig(this.getClass().getSimpleName());
    }
}

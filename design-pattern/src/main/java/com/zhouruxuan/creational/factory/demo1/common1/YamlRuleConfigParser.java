package com.zhouruxuan.creational.factory.demo1.common1;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-14
 **/
public class YamlRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
        return new RuleConfig(this.getClass().getSimpleName());

    }
}

package com.zhouruxuan.creational.abstractfactory.demo1.parse;

import com.zhouruxuan.creational.abstractfactory.demo1.RuleConfig;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-15
 **/
public interface Parse {
    RuleConfig parse(String configText);
}

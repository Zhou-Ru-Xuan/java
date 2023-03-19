package com.zhouruxuan.creational.factory.demo1.impl2;

import com.zhouruxuan.creational.factory.demo1.common1.IRuleConfigParser;
import com.zhouruxuan.creational.factory.demo1.common1.InvalidRuleConfigException;
import com.zhouruxuan.creational.factory.demo1.common1.RuleConfig;

public class RuleConfigSource {
    public static void main(String[] args) throws InvalidRuleConfigException {
        System.out.println(RuleConfigSource.load("a.json"));
        System.out.println(RuleConfigSource.load("b.properties"));
        System.out.println(RuleConfigSource.load("c.xml"));
        System.out.println(RuleConfigSource.load("d.yaml"));

    }

    public static RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
        if (parser == null) {
            throw new InvalidRuleConfigException(
                    "Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private static String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return filePath.split("\\.")[1];
    }
}
package com.zhouruxuan.creational.factory.demo1.impl1;

import com.zhouruxuan.creational.factory.demo1.common1.*;

import java.util.HashMap;

/**
 * 我们根据配置文件的后缀（json、xml、yaml、properties），选择不同的解析器（JsonRuleConfigParser、XmlRuleConfigParser……），将存储在文件中的配置解析成内存对象RuleConfig
 */
public class RuleConfigSource {
    public static void main(String[] args) throws InvalidRuleConfigException {
        System.out.println(RuleConfigSource.load("a.json"));
        System.out.println(RuleConfigSource.load("b.properties"));
        System.out.println(RuleConfigSource.load("c.xml"));
        System.out.println(RuleConfigSource.load("d.yaml"));
    }

    public static RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = createParser(ruleConfigFileExtension);

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private static String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return filePath.split("\\.")[1];
    }

    private static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}
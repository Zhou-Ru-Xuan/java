package com.zhouruxuan.creational.factory.demo1.common1;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-15
 **/
public enum ConfigFormat {
    JSON("json"),
    PROPERTIES("properties"),
    XML("xml"),
    YAML("yaml");

    ConfigFormat(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}

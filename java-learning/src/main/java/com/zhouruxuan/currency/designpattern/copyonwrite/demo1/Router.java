package com.zhouruxuan.currency.designpattern.copyonwrite.demo1;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Router {
    private final String ip;
    private final Integer port;
    private final String iface;

    //构造函数
    public Router(String ip,
                  Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }
}

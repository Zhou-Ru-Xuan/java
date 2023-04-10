package com.zhouruxuan.jvm;

import java.util.Random;

interface Client {
    boolean isVIP();
}

class Merchant {
    public double discount(double originPrice, Client client) {
        return originPrice * 0.8d;
    }
}

class Profiteer extends Merchant {
    @Override
    public double discount(double originPrice, Client client) {
        if (client.isVIP()) {                         // invokeinterface      
            return originPrice * priceRandom();                    // invokestatic
        } else {
            return super.discount(originPrice, client);          // invokespecial
        }
    }

    public static double priceRandom() {
        // 咱们的杀熟算法太粗暴了，应该将客户城市作为随机数生成器的种子。
        return new Random()                          // invokespecial
                .nextDouble()                         // invokevirtual
                + 0.8d;
    }
}
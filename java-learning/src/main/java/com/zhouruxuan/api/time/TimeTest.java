package com.zhouruxuan.api.time;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeTest {
    @Test
    public void testNs() {
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L);
        System.out.println(System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L);
        System.out.println(System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L);
        System.out.println(System.currentTimeMillis() * 1000000L + System.nanoTime() % 1000000L);
        long l = System.currentTimeMillis();
        System.out.println(++l);
        System.out.println(++l);
        System.out.println(++l);
        System.out.println(++l);
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
    }

}

package com.zhouruxuan.colletion;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    static final Map<String, String> map = new HashMap<>();
    @Test
    public void final_map_clear_test() {
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        map.clear();

        map.put("6", "6");
        Assert.assertEquals("6", map.get("6"));
    }
}

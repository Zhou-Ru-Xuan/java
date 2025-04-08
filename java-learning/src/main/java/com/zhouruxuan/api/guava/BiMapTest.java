package com.zhouruxuan.api.guava;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Assert;
import org.junit.Test;

public class BiMapTest {
    /**
     * fastjson直接反序列化
     */
    @Test
    public void fastjson_test() {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("A", 1);
        biMap.put("B", 2);
        Assert.assertEquals(new Integer(1), biMap.get("A"));
        Assert.assertEquals("B", biMap.inverse().get(2));

        // 反序列化失败
        BiMap<String, Integer> restored = JSON.parseObject(
                JSON.toJSONString(biMap),
                new TypeReference<HashBiMap<String, Integer>>() {
                }
        );
        Assert.assertNull(restored.get("A"));
        Assert.assertNull(restored.inverse().get(2));

    }

    /**
     * create
     */
    @Test
    public void biMap_create_test() {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("A", 1);
        biMap.put("B", 2);
        Assert.assertEquals(new Integer(1), biMap.get("A"));
        Assert.assertEquals("B", biMap.inverse().get(2));
    }
}

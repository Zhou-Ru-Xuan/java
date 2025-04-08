package com.zhouruxuan.api.guava;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetsTest {
    /**
     * 集合之间的对比
     */
    @Test
    public void tow_collection_relation_test() {
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        set1.add("4");
        set1.add("7");

        Set<String> set2 = new HashSet<>();
        set2.add("1");
        set2.add("4");
        set2.add("5");
        set2.add("6");

        Assert.assertEquals(Sets.newHashSet("1", "4"), Sets.intersection(set1, set2));
        Assert.assertEquals(Sets.newHashSet("2", "7"), Sets.difference(set1, set2));
        Assert.assertEquals(Sets.newHashSet("5", "6"), Sets.difference(set2, set1));
    }
}

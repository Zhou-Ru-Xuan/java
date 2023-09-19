package com.zhouruxuan.colletion;

import org.junit.Test;

import java.util.ArrayList;

public class CollectionTest {
    @Test
    public void testSize(){
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(1);
        System.out.println(arrayList.size());
    }
}

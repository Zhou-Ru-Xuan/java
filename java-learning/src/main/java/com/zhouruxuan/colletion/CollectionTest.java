package com.zhouruxuan.colletion;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionTest {
    @Test
    public void iterator_test(){
        Set set = null;
        Object firstResult = Optional.ofNullable(set).map(Set::iterator).map(Iterator::next).orElse(null);
        Assert.assertNull(firstResult);

        Set set2 = new HashSet();
        // 校验异常
        Exception exception = Assert.assertThrows(NoSuchElementException.class, () -> Optional.ofNullable(set2).map(Set::iterator).map(Iterator::next).orElse(null));
        Assert.assertNotNull(exception);
    }
    @Test
    public void testSize(){
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(1);
        System.out.println(arrayList.size());
    }
}

package com.zhouruxuan.grammer;

import entity.Animal;
import org.junit.Assert;
import org.junit.Test;

public class MethodTest {
    /**
     * 基本类型值传递
     */
    @Test
    public void basic_data_type_value_pass_test() {
        int a = 1;
        add(a);
        Assert.assertEquals(1, a);
    }

    private void add(int a) {
        a += 1;
        Assert.assertEquals(2, a);
    }

    /**
     * 引用类型传递
     */
    @Test
    public void reference_data_type_value_pass_test() {
        Animal a = new Animal();
        a.setName("cat");
        replaceName(a, "dog");
        Assert.assertEquals("dog", a.getName());

        Animal b = new Animal();
        b.setName("bird");
        newAndReplaceName(b, "pig");
        Assert.assertEquals("bird", b.getName());
    }

    private void replaceName(Animal a, String newName) {
        a.setName(newName);
        Assert.assertEquals("dog", a.getName());
    }

    private void newAndReplaceName(Animal b, String newName) {
        b = new Animal();
        b.setName(newName);
        Assert.assertEquals("pig", b.getName());
    }
}

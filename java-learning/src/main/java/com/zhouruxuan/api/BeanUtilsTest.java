package com.zhouruxuan.api;

import entity.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class BeanUtilsTest {
    /**
     * copyProperties是完全覆盖属性的
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        Animal animal = new Animal();
        animal.setAge(1);
        animal.setName("a");
        Animal animal1 = new Animal();
        animal1.setName("b");
        org.apache.commons.beanutils.BeanUtils.copyProperties(animal, animal1);
        Assertions.assertEquals("b", animal.getName());
        Assertions.assertNull(animal.getAge());


        org.springframework.beans.BeanUtils.copyProperties(animal, animal1, getNullPropertyNames(animal));
    }

    /**
     * 用spring的beanUtils，提供了一个忽略字段的方法
     */
    @Test
    public void testSpringCopyProperties() {
        Animal animal = new Animal();
        animal.setAge(1);
        animal.setName("a");
        Animal animal1 = new Animal();
        animal1.setName("b");
        myCopyProperties(animal1, animal);
        Assertions.assertEquals("b", animal.getName());
        Assertions.assertEquals(1, animal.getAge());
    }

    public static void myCopyProperties(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

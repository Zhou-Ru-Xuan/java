package com.zhouruxuan.reflection;

import entity.Animal;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionTest {

    public static void main(String[] args)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class c = Student.class;

        Constructor<Student> constructor = c.getConstructor();
        Student student = constructor.newInstance();

        Method method = c.getDeclaredMethod("print", int.class);
        method.setAccessible(true);
        method.invoke(student, 1);
    }

    @Test
    public void testSetValue() throws NoSuchFieldException, IllegalAccessException {
        Animal animal = new Animal();
        animal.setAge(1);
        HashMap<String, Object> age = new HashMap<String, Object>() {{
            put("age", 2);
        }};
        updateObject(animal, age);
        System.out.println(animal.getAge());
    }

    /**
     * 用新数据更新实例的某些字段
     *
     * @param object
     * @param dataMap
     */
    public static <T> void updateObject(T object, Map<String, Object> dataMap) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objectClass = object.getClass();

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();
            Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        }
    }
}
package com.zhouruxuan.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

  public static void main(String[] args)
      throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
    Class c = Student.class;

    Constructor<Student> constructor = c.getConstructor();
    Student student = constructor.newInstance();

    Method method = c.getDeclaredMethod("print",int.class);
    method.setAccessible(true);
    method.invoke(student,1);
  }
}
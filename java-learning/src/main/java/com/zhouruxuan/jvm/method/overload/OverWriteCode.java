package com.zhouruxuan.jvm.method.overload;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.Translator;

public class OverWriteCode {
    public static void main(String[] args) {
        updateUserClass();
    }

    public static void updateUserClass() {
        try {
            //获取ClassPool
            ClassPool pool = ClassPool.getDefault();
            //获取User类
            CtClass ctClass = pool.get(User.class.getName());
            CtMethod cm = ctClass.getDeclaredMethod("study", null);
            cm.setBody("{" + "System.out.println(\"你好：\");" + ""
                    + "return \"123\";}");

            ctClass.addMethod(CtMethod.make("public void study() {\n"
                    + "    System.out.println(\"study\");\n"
                    + "  }", ctClass));
            //这里会将这个创建的类对象编译为.class文件
            ctClass.writeFile("./path/");

            Translator translator = new Translator() {
                @Override
                public void start(ClassPool classPool) {
                    System.out.println("start");
                }

                @Override
                public void onLoad(ClassPool classPool, String paramString) {
                    System.out.println("onLoad：" + paramString); //com.msdn.java.hotspot.byteCode.User
                    new User().study();//调用的是原始类的方法
                }
            };
            Loader classLoader = new Loader(pool); //Javassist 提供的 Classloader
            classLoader.addTranslator(pool, translator); //监听 ClassLoader 的生命周期

            Class uClass = classLoader.loadClass(User.class.getName());
            Object instance = uClass.newInstance();
            uClass.getDeclaredMethod("study").invoke(instance);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


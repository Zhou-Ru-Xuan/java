package com.zhouruxuan.currency.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-26
 **/
public class NewThreadTest {
    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }

    @Test
    public void extendsTest() {
        new A().start();
        new A().run();

    }

    static class B implements Runnable {
        @Override
        public void run() {
            System.out.println("B");
        }
    }

    @Test
    public void runnableTest() {
        B b = new B();
        new Thread(b).start();
    }

    static class C implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "C";
        }
    }

    @Test
    public void callableTest() {
        //Thread没有Callable的构造器，所以只能用线程池创建
        //new Thread(new C()).start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new C());
        try {
            String result = future.get();
            System.out.println("Callable result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    @Test
    public void lambdaTest(){
        Thread a = new Thread(() -> System.out.println("a"));
        a.start();
    }

}

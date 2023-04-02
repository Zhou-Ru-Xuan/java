package com.zhouruxuan.currency.util;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-31
 **/
public class CompletableFutureTest {
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> demo = new CompletableFuture<>();
        demo.complete("CompletableFuture");
        System.out.println(demo.get());

        CompletableFuture<String> demo2 = CompletableFuture.completedFuture("completedFuture");
        System.out.println(demo2.get());

        CompletableFuture.runAsync(()-> System.out.println(Thread.currentThread().getName()));

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(demo2, demo);
        System.out.println(objectCompletableFuture.get());
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        CompletableFuture<String> demo = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something by thread" + Thread.currentThread().getName());
            return "success";
        });
        System.out.println(demo.get());

        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> demo2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something by thread" + Thread.currentThread().getName());
            return "success";
        }, executor);
        System.out.println(demo2.get());

        CompletableFuture.runAsync(() -> {
            System.out.println("do something by thread" + Thread.currentThread().getName());
        });
    }

    @Test
    public void test03() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "【步骤1的执行结果】";
        }, executor);

        CompletableFuture<String> step2 = step1.thenApply(result -> {
            System.out.println("上一步操作结果为：" + result);
            return "【步骤2的执行结果】";
        });
        System.out.println("步骤2的执行结果：" + step2.get());


    }

    @Test
    public void test04() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            return "【步骤1的执行结果】";
        }, executor);

        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);

        CompletableFuture<String> step3 = step1.thenCombine(step2, (result1, result2) -> {
            System.out.println("前两步操作结果分别为：" + result1 + result2);
            return "【步骤3的执行结果】";
        });

        System.out.println("步骤3的执行结果：" + step3.get());


    }

    @Test
    public void test05() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            return "【步骤1的执行结果】";
        }, executor);
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);
        CompletableFuture<String> step3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤3】");
            return "【步骤3的执行结果】";
        }, executor);

        CompletableFuture<Void> stepM = CompletableFuture.allOf(step1, step2, step3);
        CompletableFuture<String> stepMResult = stepM.thenApply(res -> {
            // 通过join函数获取返回值
            String result1 = step1.join();
            String result2 = step2.join();
            String result3 = step3.join();

            return result1 + result2 + result3;
        });
        System.out.println("步骤M的结果：" + stepMResult.get());

        CompletableFuture<Object> stepM2 = CompletableFuture.anyOf(step1, step2, step3);
        System.out.println("步骤M的结果：" + stepM2.get());
    }


    @Test
    public void test06() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFuture<String> A = CompletableFuture.completedFuture("Q");
        CompletableFuture<String> B = A.thenApply(a -> {
            System.out.println("B");
            return "B";
        });
        CompletableFuture<String> C = A.thenApply(a -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("C");
            return "C";
        });
        CompletableFuture<String> B1 = A.thenApply(a -> {
            System.out.println("B1");
            return "B";
        });
        CompletableFuture<String> B2 = A.thenApply(a -> {
            System.out.println("B2");
            return "B";
        });
        CompletableFuture<String> B3 = A.thenApply(a -> {
            System.out.println("B3");
            return "B";
        });

    }

    @Test
    public void test07() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFuture<String> A = new CompletableFuture<>();
        CompletableFuture<String> B = A.thenApply(a -> {
            System.out.println("B");
            return "B";
        });
        CompletableFuture<String> C = A.thenApply(a -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("C");
            return "C";
        });
        A.complete("Q");
    }

    @Test
    public void test08() {
        ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            //业务操作
            return "";
        }, threadPool1);
        //此时，如果future1中的业务操作已经执行完毕并返回，则该thenApply直接由当前main线程执行；否则，将会由执行以上业务操作的threadPool1中的线程执行。
        cf1.thenApply(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });
        //使用ForkJoinPool中的共用线程池CommonPool
        cf1.thenApplyAsync(value -> {
        //do something
            System.out.println("thenApplyAsync CommonPool  执行线程：" + Thread.currentThread().getName());
            return value + "1";
        });
        //使用指定线程池
        cf1.thenApplyAsync(value -> {
        //do something
            System.out.println("thenApplyAsync CommonPool 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        }, threadPool1);
    }

    @Test
    public void test09() throws ExecutionException, InterruptedException {
        ExecutorService threadPool1 = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync 执行线程：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //业务操作
            return "";
        }, threadPool1);
        //此时，如果future1中的业务操作已经执行完毕并返回，则该thenApply直接由当前main线程执行；否则，将会由执行以上业务操作的threadPool1中的线程执行。
        CompletableFuture<String> stringCompletableFuture = cf1.thenApplyAsync(value -> {
            System.out.println("thenApply 执行线程：" + Thread.currentThread().getName());
            return value + "1";
        },threadPool1);
    }
}

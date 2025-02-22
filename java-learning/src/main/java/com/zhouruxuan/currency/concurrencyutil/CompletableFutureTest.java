package com.zhouruxuan.currency.concurrencyutil;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-31
 **/
public class CompletableFutureTest {
    @Test
    public void test_get() {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            throw new MyException("内部异常");
        });
        String msg = "";
        try {
            Object o = future.get();
        } catch (MyException e) {
            if (e.getCause() instanceof MyException) {
                msg = e.getCause().getMessage()+"myexe";
            } else {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            if (e.getCause() instanceof MyException) {
                msg = e.getCause().getMessage()+"exe";
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("内部异常exe", msg);
    }

    class MyException extends RuntimeException {
        String msg;

        public MyException(String msg) {
            super(msg);
            this.msg = msg;
        }
    }

    /*
    一个依赖两个的测试
     */
    @Test
    public void one_depend_on_two_test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "," + 1);
            return 1;
        }, executor);

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "," + 2);
            return 2;
        }, executor);

        CompletableFuture<Integer> cf3 = cf1.thenCombineAsync(cf2, (a, b) -> {
            return a + b;
        }).thenApplyAsync(r1 -> {
                    if (r1 != 3) {
                        System.out.println(Thread.currentThread().getName() + "," + r1);
                        return r1;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "," + 9);
                        return 9;
                    }

                }
                , executor);
        Integer integer = cf3.get();
        System.out.println(integer);
    }

    /**
     * 这个测试方法主要是为了展示使用Future时可能遇到的回调地狱问题。
     * 通过使用Guava提供的ListenableFuture和Futures，我们可以将任务提交到线程池执行，并在任务完成后获取结果。
     * 但是，如果我们需要在任务完成后继续执行一些操作（例如，我们需要在两个任务都完成后执行第三个任务），那么代码就会变得复杂且难以理解，这就是所谓的回调地狱。
     * 在这个方法中，我们首先创建了一个线程池，然后提交了两个任务（step 1和step 2）到线程池执行。
     * 我们使用Futures.allAsList将两个任务的Future合并为一个，然后添加了一个回调函数，当两个任务都完成时，打印出两个任务的结果，并提交第三个任务（step 3）到线程池执行。
     * 对于第三个任务，我们也添加了一个回调函数，当任务完成时，打印出任务的结果。
     * 这样，我们就可以在任务完成后执行一些操作，但是代码变得复杂且难以理解。
     */
    @Test
    public void testFutureProblem() {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 使用Guava提供的MoreExecutors.listeningDecorator方法将普通的线程池包装为ListeningExecutorService
        ListeningExecutorService guavaExecutor = MoreExecutors.listeningDecorator(executor);
        // 提交任务到线程池执行，并获取ListenableFuture
        ListenableFuture<String> future1 = guavaExecutor.submit(() -> {
            //step 1
            System.out.println("执行step 1");
            return "step1 result";
        });
        // 提交任务到线程池执行，并获取ListenableFuture
        ListenableFuture<String> future2 = guavaExecutor.submit(() -> {
            //step 2
            System.out.println("执行step 2");
            return "step2 result";
        });
        // 使用Futures.allAsList将两个任务的Future合并为一个
        ListenableFuture<List<String>> future1And2 = Futures.allAsList(future1, future2);
        // 添加回调函数，当两个任务都完成时，打印出两个任务的结果，并提交第三个任务到线程池执行
        Futures.addCallback(future1And2, new FutureCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                System.out.println(result);
                // 提交第三个任务到线程池执行，并获取ListenableFuture
                ListenableFuture<String> future3 = guavaExecutor.submit(() -> {
                    System.out.println("执行step 3");
                    return "step3 result";
                });
                // 添加回调函数，当任务完成时，打印出任务的结果
                Futures.addCallback(future3, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                }, guavaExecutor);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        }, guavaExecutor);
    }


    /**
     * 使用CompletableFuture优化上面的回调问题
     */
    @Test
    public void testCfBetter() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            return "step1 result";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            return "step2 result";
        });
        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + " , " + result2);
            System.out.println("执行step 3");
            return "step3 result";
        }).thenAccept(System.out::println);
    }

    /**
     * 测试complete和get方法
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> demo = new CompletableFuture<>();
        demo.complete("CompletableFuture");
        System.out.println(demo.get());

        CompletableFuture<String> demo2 = CompletableFuture.completedFuture("completedFuture");
        System.out.println(demo2.get());

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(demo2, demo);
        System.out.println(objectCompletableFuture.get());
    }

    /**
     * 使用线程池执行任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

    /**
     * 串行执行
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test03() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            try {
                Thread.sleep(5000); //假装在执行逻辑
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "【步骤1的执行结果】";
        }, executor);

        CompletableFuture<String> step2 = step1.thenApply(result -> {
            try {
                Thread.sleep(5000); //假装在执行逻辑
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("上一步操作结果为：" + result);
            return "【步骤2的执行结果】";
        });
        System.out.println("步骤2的执行结果：" + step2.get());


    }

    /**
     * 合并结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

    /**
     * 依赖前面的多个结果/或者以来前面多个结果中的某一个（短路）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void allOf() throws ExecutionException, InterruptedException {
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


    }

    /**
     * 依赖前面的多个结果/或者以来前面多个结果中的某一个（短路）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void allOfAsync() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行【步骤1】");
            return "【步骤1的执行结果】";
        }, executor);
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);
        CompletableFuture<String> step3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行【步骤3】");
            return "【步骤3的执行结果】";
        }, executor);

        CompletableFuture<Void> stepM = CompletableFuture.allOf(step1, step2, step3);
        CompletableFuture<Void> stepMResult = stepM.thenRunAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 通过join函数获取返回值
            String result1 = step1.join();
            String result2 = step2.join();
            String result3 = step3.join();
            System.out.println(result1 + result2 + result3);
        }, executor);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("waiting....");
        }
    }

    /**
     * 依赖前面的多个结果/或者以来前面多个结果中的某一个（短路）
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void anyOf() throws ExecutionException, InterruptedException {
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

        CompletableFuture<Object> stepM2 = CompletableFuture.anyOf(step1, step2, step3);
        System.out.println("步骤M的结果：" + stepM2.get());
    }

    /**
     * 1. 和栈的性质一样，越晚添加的编排逻辑越早被执行
     * 2. 基于同一个对象衍生出来的流程节点的源任务是一致的
     */
    @Test
    public void test06() {

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

        A.complete("finish"); //输出结果

    }

    /**
     * exceptionally只能拿到异常。拥有返回值
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testHandleException() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    throw new RuntimeException();
                }
        );

        CompletableFuture<String> exceptionFuture = completableFuture.exceptionally((e) -> {
            e.printStackTrace();
            return "你的程序异常啦";
        });

        System.out.println(exceptionFuture.get());
    }

    /**
     * 对异常进行转换
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testHandleException1() throws ExecutionException, InterruptedException {
        String name = null;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                    if (name == null) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "Hello, " + name;
                })
                .handle((result, throwable) -> result != null ? result : "Hello, Stranger!");

        assertEquals("Hello, Stranger!", completableFuture.get());
    }

    /**
     * 对异常进行处理
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testHandleException2() {
        ExecutorService executor = Executors.newFixedThreadPool(4, r -> new Thread(r, "customer"));
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello, World!";
        }, executor);

        CompletableFuture<String> completableFuture = future.whenComplete((result, error) -> {
            System.out.println(Thread.currentThread().getName());
            if (error != null) {
                fail("Future completed exceptionally", error);
            } else {
                assertEquals("Hello, World!", result);
            }
        });

        Assertions.assertEquals(future, completableFuture);
    }

    @Test
    public void testNoException() {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int a = 0;
            int b = 666;
            int c = b / a;
            return true;
        }, executorService).thenAccept(System.out::println);

        //如果不加 get()方法这一行，看不到异常信息
        //future.get();

    }

    /**
     * 测试join方法能不能抛出异常
     */
    @Test
    public void testHandleExceptionWithJoin() {
        List<CompletableFuture<String>> futureList = Lists.newArrayListWithExpectedSize(3);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤1】");
            throw new RuntimeException("步骤1执行异常");
        }, executor);
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤2】");
            return "【步骤2的执行结果】";
        }, executor);
        CompletableFuture<String> step3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤3】");
            return "【步骤3的执行结果】";
        }, executor);
        CompletableFuture<String> step4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行【步骤4】");
            return "【步骤4的执行结果】";
        }, executor);
        futureList.add(step1);
        futureList.add(step2);
        futureList.add(step3);
        futureList.add(step4);

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

    }

    /**
     * 测试异步中添加CF
     */
    @Test
    public void testAsyncAddCf() {
        List<CompletableFuture> futures = new ArrayList<>();


        futures.add(CompletableFuture.runAsync(() -> addInCf(futures, "one")));
        futures.add(CompletableFuture.runAsync(() -> addInCf(futures, "two")));
        futures.add(CompletableFuture.runAsync(() -> addInCf(futures, "three")));
        while (futures.size() < 6) {
            System.out.println(futures.size());
        } //空轮询等待内部填充完futures
        System.out.println("add end");
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    void addInCf(List<CompletableFuture> futures, String result) {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        futures.add(CompletableFuture.runAsync(() -> System.out.println(result)));
    }
}

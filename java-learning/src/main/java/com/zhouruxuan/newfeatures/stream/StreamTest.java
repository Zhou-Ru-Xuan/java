package com.zhouruxuan.newfeatures.stream;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class StreamTest {
    @Test
    public void test1() {
        List<Integer> list = List.of(1, 2, 3);

        List<Integer> filterList = new ArrayList<>();

        for (Integer i : list) {
            if (i > 2) {
                filterList.add(i);
            }
        }

        System.out.println(filterList);

        List<Integer> list2 = List.of(1, 2, 3);

        List<Integer> filterList2 = new ArrayList<>();

        for (Integer i : list2) {
            if (i > 2 && i < 10 && (i % 2 == 0)) {
                filterList2.add(i);
            }
        }

        System.out.println(filterList2);


    }

    @Test
    public void test2() {
        List<Integer> objects = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        objects.forEach(System.out::println);
        List<Integer> collect = objects.stream().sorted().collect(Collectors.toList());
    }

    @Test
    public void test3() {
        IntStream.range(1, 10)
                .peek(x -> System.out.print(" A" + x))
                .limit(3)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));
    }

    @Test
    public void test4() {
        IntStream.range(1, 10)
                .peek(x -> System.out.print(" A" + x))
                .skip(6)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));
    }

    @Test
    public void parallelStreamTest() throws ExecutionException, InterruptedException {
        IntStream list = IntStream.range(0, 10);
        Set<Thread> threadSet = new HashSet<>();
        //开始并行执行
        list.parallel().forEach(i -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer：" + i + "，" + "currentThread:" + thread.getName());
            threadSet.add(thread);
        });
        System.out.println("all threads：" + Joiner.on("，").join(threadSet.stream().map(Thread::getName).collect(Collectors.toList())));


        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());// 输出 12
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());// 输出 12

        // 获取当前机器CPU处理器的数量
        System.out.println(Runtime.getRuntime().availableProcessors());// 输出 4
        // parallelStream默认的并发线程数
        System.out.println(ForkJoinPool.getCommonPoolParallelism());// 输出 3
    }
}

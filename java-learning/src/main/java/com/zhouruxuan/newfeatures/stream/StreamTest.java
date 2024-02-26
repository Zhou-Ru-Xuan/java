package com.zhouruxuan.newfeatures.stream;

import com.google.common.base.Joiner;
import entity.LogicRoom;
import entity.RoomType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class StreamTest {
    /**
     * 验证orElse的作用范围
     */
    @Test
    public void testOptional() {
        String s = "";
        String aDefault = Optional.ofNullable(s).filter(StringUtils::isNotBlank).orElse("default");
        String bDefault = Optional.ofNullable(s).orElse("default");
        Assertions.assertEquals("default", aDefault);
        Assertions.assertEquals(s, bDefault);

        LogicRoom logicRoom = new LogicRoom();
        Long roomId = null;
        try {
            roomId = logicRoom.getRoomType().getRoomId();
        } catch (Exception e) {
            Assertions.assertNull(roomId);
        }
        Long aDefault1 = Optional.ofNullable(logicRoom)
                .map(LogicRoom::getRoomType)
                .map(RoomType::getRoomId)
                .orElse(888L);
        Assertions.assertEquals(888L, aDefault1);

        Long aDefault2 = Optional.of(logicRoom)
                .map(LogicRoom::getRoomType)
                .map(RoomType::getRoomId)
                .orElse(888L);
        Assertions.assertEquals(888L, aDefault2);
    }

    @Test
    public void testForEach() {
        // 使用Stream.forEach()迭代
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.forEach(str -> System.out.print(str));
    }

    @Test
    public void testFilter() {
        // 保留长度等于3的字符串
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.filter(str -> str.length() == 3).forEach(str -> System.out.print(str));
    }

    @Test
    public void testMap() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.map(str -> str.toUpperCase()).forEach(str -> System.out.print(str));
    }

    @Test
    public void testFlatMap() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream()).forEach(i -> System.out.print(i));
    }

    @Test
    public void testLimit() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        stream.limit(2).forEach(i -> System.out.print(i));//12
    }

    @Test
    public void testPeek() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        stream.peek(System.out::print).collect(Collectors.toList());
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


        List<Integer> values = new ArrayList<>();
        IntStream.range(1, 10000).parallel().forEach(values::add);
        System.out.println(values.size());

        ArrayList<Object> arrayList = new ArrayList();
        List<Object> collect = arrayList.stream().parallel().collect(Collectors.toList());
    }

    @Test
    public void parallelStreamTest2() throws ExecutionException, InterruptedException {
        List<Object> list = Arrays.asList(new Object(), new Object(), new Object());
        Set<Thread> threadSet = new HashSet<>();
        //开始并行执行
        list.stream().parallel().forEach(i -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer：" + i + "，" + "currentThread:" + thread.getName());
            threadSet.add(thread);
        });
        System.out.println("all threads：" + Joiner.on("，").join(threadSet.stream().map(Thread::getName).collect(Collectors.toList())));

    }

    @Test
    public void testDistinct() {
        ArrayList<Object> objects = new ArrayList<>();
        Object o = new Object();
        Object o2 = new Object();
        objects.add(o);
        objects.add(o);
        objects.add(o2);
        objects.add(null);
        List<Object> collect = objects.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testPartitionBy() {
        List<Long> goodsIds = Arrays.asList(1L, 2L, 3L, 2L, 4L, 3L, 5L, 6L, 7L, 8L, 9L, 10L);

        int batchSize = 2;
        List<List<Long>> collect = goodsIds.stream()
                .distinct()
                .collect(Collectors.partitioningBy(i -> batchSize < goodsIds.size() / batchSize))
                .values()
                .stream()
                .map(list -> list.stream().collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (List<Long> longs : collect) {
            System.out.println(longs);
        }
    }

    @Test
    public void testGroupBy() {
        List<Long> goodsIds = Arrays.asList(1L, 2L, 3L, 2L, 4L, 3L, 5L, 6L, 7L, 8L, 9L, 10L);
        // 假设goodsIds有1000个元素

        // 过滤掉重复元素
        List<Long> distinctGoodsIds = goodsIds.stream()
                .distinct()
                .collect(Collectors.toList());

        // 按照每500个元素一批进行分组
        Map<Integer, List<Long>> batches = distinctGoodsIds.stream()
                .collect(Collectors.groupingBy(i -> (distinctGoodsIds.indexOf(i) / 2) + 1));

        // 输出分组结果
        batches.forEach((batchNumber, batch) -> {
            System.out.println("Batch " + batchNumber + ": " + batch);
        });
    }
}

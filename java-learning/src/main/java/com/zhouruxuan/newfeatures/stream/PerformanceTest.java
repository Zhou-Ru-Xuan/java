package com.zhouruxuan.newfeatures.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-27
 **/
public class PerformanceTest {
    @Test
    public void test1(){
        //Calculating square root of even numbers from 1 to N
        int min = 1;
        int max = 1000000;

        List<Integer> sourceList = new ArrayList<>();
        for (int i = min; i < max; i++) {
            sourceList.add(i);
        }

        List<Double> result = new LinkedList<>();


        //Collections approach
        long t0 = System.nanoTime();
        long elapsed = 0;
        for (Integer i : sourceList) {
            if(i % 2 == 0){
                result.add(Math.sqrt(i));
            }
        }
        elapsed = System.nanoTime() - t0;
        System.out.printf("Collections: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Stream approach
        Stream<Integer> stream = sourceList.stream();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(Math::sqrt).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Streams: Elapsed time:\t\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));


        //Parallel stream approach
        stream = sourceList.stream().parallel();
        t0 = System.nanoTime();
        result = stream.filter(i -> i%2 == 0).map(Math::sqrt).collect(Collectors.toList());
        elapsed = System.nanoTime() - t0;
        System.out.printf("Parallel streams: Elapsed time:\t %d ns \t(%f seconds)%n", elapsed, elapsed / Math.pow(10, 9));
    }
}

package com.zhouruxuan.newfeatures.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
}

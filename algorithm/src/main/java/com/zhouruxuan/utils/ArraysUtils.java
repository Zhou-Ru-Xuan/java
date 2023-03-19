package com.zhouruxuan.utils;

import java.util.ArrayList;

/**
 * @author zhouruxuan
 * @date 2022/11/10 09:26
 * @description
 */
public class ArraysUtils {
    public static int[][] deserialize(String a) {
        a = a.substring(1, a.length() - 1);
        ArrayList<int[]> ints = new ArrayList<>();
        int i = 0;
        while (i < a.length()) {
            int x = a.charAt(i + 1) - '0';
            int y = a.charAt(i + 3) - '0';
            ints.add(new int[]{x, y});
            i += 6;
        }
        int[][] res = new int[ints.size()][2];
        for (int i1 = 0; i1 < ints.size(); i1++) {
            res[i1] = ints.get(i1);
        }
        return res;
    }
}

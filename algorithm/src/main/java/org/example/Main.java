package org.example;


import com.zhouruxuan.utils.ArraysUtils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    static Solution solution = new Solution();

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(solution.findOrder(3, ArraysUtils.deserialize("[[1,0],[2,1]]"))));

    }


}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] inDegree = new int[numCourses];
        int[][] matrix = new int[numCourses][numCourses];
        int[] resArr = new int[numCourses];
        int resIndex = 0;
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[1];
            int last = prerequisite[0];
            inDegree[last]++;
            matrix[pre][last] = 1;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) deque.add(i);
        }
        while (!deque.isEmpty()) {
            Integer pre = deque.poll();
            for (int i = 0; i < matrix[pre].length; i++) {
                if (matrix[pre][i] == 0) continue;
                if (inDegree[i] == 1) {
                    deque.add(i);
                } else {
                    inDegree[i]--;
                }
            }
            resArr[resIndex++] = pre;
        }
        if (numCourses == resIndex) {
            return resArr;
        }
        return new int[]{};
    }
}
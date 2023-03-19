package org.example;


import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static Solution solution = new Solution();

    public static void main(String[] args) throws IOException {
        System.out.println(Long.valueOf(null));
    }

    private void test1(){
        System.out.println(this.getClass().getName());
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        var deque = new PriorityQueue<Integer>((i1, i2) -> costs[i1] == costs[i2] ? i1 - i2 : costs[i1] - costs[i2]);
        int i = 0;
        int j = costs.length - 1;
        if (2 * candidates >= costs.length) {
            for (int l = 0; l < costs.length; l++) {
                deque.add(l);
            }
            long res = 0L;
            for (int l = 0; l < k; l++) {
                res += costs[deque.poll()];
            }
            return res;
        } else {
            for (int l = 0; l < candidates; l++) {
                deque.add(i++);
                deque.add(j--);
            }
            long res = 0L;
            for (int l = 0; l < k; l++) {
                Integer index = deque.poll();
                res += costs[index];
                if (index > j && i <= j) deque.add(j--);
                else if (index < i && i <= j) deque.add(i++);
            }
            return res;
        }

    }
}
package com.zhouruxuan.tree.segmenttree;

import java.util.*;

/**
 * @author zhouruxuan
 * @date 2022/9/29 16:42
 */
public class SingleUpdateMaxDiscrete {

    int[] tree;//线段树数组

    private final int ROOT_NUM = 1; //完全二叉树中根节点的编号
    private final int START;//搜索的左边界
    private final int END;//搜索的右边界

    Map<Integer, Integer> map = new HashMap<>();

    public SingleUpdateMaxDiscrete(int[] nums) {
        int n = this.discrete(nums);
        this.START = 0;
        this.END = n - 1;
        this.tree = new int[n * 4];

    }

    private int discrete(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        ArrayList<Integer> integers = new ArrayList<>(set);
        integers.sort(Comparator.comparingInt(i -> i));
        int index = 0;
        for (Integer integer : integers) {
            map.put(integer, index++);
        }
        return index;
    }

    /**
     * @param key
     * @param value
     */
    public void update(int key, int value) {
        update(map.get(key), value, START, END, ROOT_NUM);
    }

    public int queryMax(int l, int r) {
        return queryMax(map.get(l), map.get(r), START, END, ROOT_NUM);
    }

    private void update(int key, int value, int start, int end, int i) {
        if (start == end) {
            tree[i] = Math.max(tree[i], value);
            return;
        }

        int mid = start + (end - start) / 2;
        if (key <= mid) {
            update(key, value, start, mid, 2 * i);
        } else {
            update(key, value, mid + 1, end, 2 * i + 1);
        }
        pushUp(i);
    }

    private int queryMax(int l, int r, int start, int end, int i) {
        if (l <= start && end <= r) {
            return tree[i];
        }
        int mid = start + (end - start) / 2;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        if (l <= mid) {
            leftMax = queryMax(l, r, start, mid, 2 * i);
        }
        if (r > mid) {
            rightMax = queryMax(l, r, mid + 1, end, 2 * i + 1);
        }
        return Math.max(leftMax, rightMax);
    }

    private void pushUp(int i) {
        tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
    }

}

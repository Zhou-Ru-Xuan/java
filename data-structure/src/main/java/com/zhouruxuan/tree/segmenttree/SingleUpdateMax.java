package com.zhouruxuan.tree.segmenttree;

/**
 * @author zhouruxuan
 * @date 2022/9/29 08:04
 * @description 求区间最大值，动态地加入节点，可解决： 最长递增子序列 II https://leetcode.cn/problems/longest-increasing-subsequence-ii/
 */
class SingleUpdateMax {

    int[] tree;//线段树数组
    private final int ROOT_NUM = 1; //完全二叉树中根节点的编号
    private final int START;//搜索的左边界
    private final int END;//搜索的右边界

    public SingleUpdateMax(int start, int end) {
        this.START = start;
        this.END = end;
        this.tree = new int[END * 4];
    }


    public void update(int key, int value) {
        update(key, value, START, END, ROOT_NUM);
    }


    public int queryMax(int l, int r) {
        return queryMax(l, r, START, END, ROOT_NUM);
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

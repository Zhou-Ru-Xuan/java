package com.zhouruxuan.tree.segmenttree;

/**
 * @author zhouruxuan
 * @date 2022/9/29 14:24
 * @description 区间求和线段树，可以解决 307. 区域和检索 - 数组可修改 ： https://leetcode.cn/problems/range-sum-query-mutable/
 */
class SingleUpdateSum {
    int[] tree;
    int[] nums;
    private final int ROOT_NUM = 1; //完全二叉树中根节点的编号
    private final int START;//搜索的左边界
    private final int END;//搜索的右边界


    public SingleUpdateSum(int[] nums) {
        this.START = 0;
        this.END = nums.length - 1;
        this.nums = nums;
        this.tree = new int[nums.length * 4];

    }

    private void buildTree() {
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int index, int val) {
        update(index, val, START, END, ROOT_NUM);
    }

    public int sumRange(int left, int right) {
        return sumRange(left, right, START, END, ROOT_NUM);
    }

    public void update(int index, int val, int l, int r, int i) {
        if (l == r) {
            tree[i] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            update(index, val, l, mid, 2 * i);
        } else {
            update(index, val, mid + 1, r, 2 * i + 1);
        }
        pushUp(i);
    }

    private void pushUp(int i) {
        tree[i] = tree[2 * i] + tree[2 * i + 1];
    }

    public int sumRange(int left, int right, int l, int r, int i) {
        if (left <= l && right >= r) {
            return tree[i];
        }
        int mid = l + (r - l) / 2;
        int leftSum = 0;
        int rightSum = 0;
        if (left <= mid) {
            leftSum = sumRange(left, right, l, mid, 2 * i);
        }
        if (right > mid) {
            rightSum = sumRange(left, right, mid + 1, r, 2 * i + 1);
        }
        return leftSum + rightSum;
    }
}

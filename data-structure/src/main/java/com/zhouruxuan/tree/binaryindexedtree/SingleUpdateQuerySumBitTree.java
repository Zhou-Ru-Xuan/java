package com.zhouruxuan.tree.binaryindexedtree;

/**
 * @author zhouruxuan
 * @date 2022/10/8 17:42
 * @description 单点修改，区间查询。参考例题：https://leetcode.cn/problems/range-sum-query-mutable/
 */
class SingleUpdateQuerySumBitTree {
    int n;
    int[] nums;
    int[] tree;

    public SingleUpdateQuerySumBitTree(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int value) {
        add(index + 1, -nums[index] + value);
        this.nums[index] = value;
    }

    /**
     * 查询区间[l,r]的和
     *
     * @param l
     * @param r
     * @return
     */
    public int query(int l, int r) {
        return query(r + 1) - query(l);
    }

    private void add(int index, int value) {
        for (int i = index; i <= n; i += lowbit(i)) {
            tree[i] += value;
        }
    }

    /**
     * 查询前缀[0,end]的和
     *
     * @param end
     * @return
     */
    private int query(int end) {
        int sum = 0;
        for (int i = end; i >= 1; i -= lowbit(i)) {
            sum += tree[i];
        }
        return sum;
    }

    private int lowbit(int i) {
        return i & (-i);
    }

}
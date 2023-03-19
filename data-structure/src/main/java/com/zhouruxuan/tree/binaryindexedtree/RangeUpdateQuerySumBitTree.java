package com.zhouruxuan.tree.binaryindexedtree;

/**
 * @author zhouruxuan
 * @date 2022/10/8 20:23
 */
class RangeUpdateQuerySumBitTree {
    int n;
    int[] tree;
    int[] diff;
    int[] helperTree;

    public RangeUpdateQuerySumBitTree(int[] nums) {
        this.n = nums.length;
        tree = new int[n + 1];
        diff = new int[n + 1];
        helperTree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                diff[i + 1] = nums[0];
            } else {
                diff[i + 1] = nums[i] - nums[i - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            add(i, tree, diff[i]);
            add(i, helperTree, (i - 1) * diff[i]);
        }

    }

    /**
     * 对区间[l,r]增加value
     *
     * @param l
     * @param r
     * @param value
     */
    public void add(int l, int r, int value) {
        add(l, tree, value);
        add(r + 1, tree, -value);
        add(l, helperTree, (l - 1) * value);
        add(r + 1, helperTree, r * -1 * value);
    }

    private void add(int index, int[] a, int value) {
        for (int i = index; i <= n; i += lowbit(i)) {
            a[i] += value;
        }
    }

    /**
     * 查询区间[l,r]的和
     *
     * @param l
     * @param r
     * @return
     */
    public int query(int l, int r) {
        int preSumL = (l - 1) * query(tree, l - 1) - query(helperTree, l - 1);
        int preSumR = r * query(tree, r) - query(helperTree, r);
        return preSumR - preSumL;
    }

    /**
     * 查询区间[0,index]的区间和
     *
     * @param a
     * @param index
     * @return
     */
    private int query(int[] a, int index) {
        int sum = 0;
        for (int i = index; i >= 1; i -= lowbit(i)) {
            sum += a[i];
        }
        return sum;
    }

    private int lowbit(int i) {
        return i & (-i);
    }
}

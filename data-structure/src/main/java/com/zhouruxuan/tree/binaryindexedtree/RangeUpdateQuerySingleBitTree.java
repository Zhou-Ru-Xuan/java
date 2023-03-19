package com.zhouruxuan.tree.binaryindexedtree;

/**
 * @author zhouruxuan
 * @date 2022/10/8 18:24
 * @descripton 区间修改，单点查询
 */
public class RangeUpdateQuerySingleBitTree {
    int n;
    int[] diff;
    int[] tree;

    public RangeUpdateQuerySingleBitTree(int[] nums) {
        this.n = nums.length;
        this.diff = new int[n + 1];
        this.tree = new int[n + 1];
        diff[1] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i + 1] = nums[i] - nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            add(i, diff[i]);
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
//        this.diff[l] += value;
//        this.diff[r + 1] -= value;
        add(l, value);
        add(r + 1, -value);
    }

    /**
     * 查询下标为index的值
     *
     * @param index
     * @return
     */
    public int query(int index) {
        int sum = 0;
        for (int i = index; i >= 1; i -= lowbit(i)) {
            sum += this.tree[i];
        }
        return sum;
    }

    private void add(int index, int value) {
        for (int i = index; i < n + 1; i += lowbit(i)) {
            this.tree[i] += value;
        }
    }

    private int lowbit(int i) {
        return i & (-i);
    }


}

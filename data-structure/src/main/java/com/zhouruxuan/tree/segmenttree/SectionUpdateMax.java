package com.zhouruxuan.tree.segmenttree;

/**
 * @author zhouruxuan
 * @date 2022/9/29 17:07
 */

public class SectionUpdateMax {

    int[] tree;//线段树数组
    int[] lazy;//懒惰更新的值
    private final int ROOT_NUM = 1; //完全二叉树中根节点的编号
    private final int START;//搜索的左边界
    private final int END;//搜索的右边界

    public SectionUpdateMax(int start, int end) {
        this.START = start;
        this.END = end;
        this.tree = new int[4 * END];
        this.lazy = new int[4 * END];
    }


    public void updateOne(int key, int value) {
        updateOne(key, value, START, END, ROOT_NUM);
    }

    public void updateSection(int l, int r, int value) {
        updateSection(l, r, value, START, END, ROOT_NUM);
    }


    public int queryMax(int l, int r) {
        return queryMax(l, r, START, END, ROOT_NUM);
    }

    private void updateOne(int key, int value, int start, int end, int i) {
        if (start == end) {
            tree[i] = Math.max(tree[i], value);
            return;
        }

        int mid = start + (end - start) / 2;
        if (key <= mid) {
            updateOne(key, value, start, mid, 2 * i);
        } else {
            updateOne(key, value, mid + 1, end, 2 * i + 1);
        }
        pushUp(i);
    }

    private void updateSection(int l, int r, int value, int start, int end, int i) {
        if (l <= start && end <= r) {
            tree[i] = Math.max(tree[i], value);
            if (start != end) {
                lazy[i] = value;
            }
            //通过lazy标记完就可以直接返回了
            return;
        }

        if (lazy[i] != 0) {
            pushDown(i);
        }

        int mid = start + (end - start) / 2;
        if (l <= mid) {
            updateSection(l, r, value, start, mid, 2 * i);
        }
        if (r > mid) {
            updateSection(l, r, value, mid + 1, end, 2 * i + 1);
        }
        pushUp(i);
    }


    private int queryMax(int l, int r, int start, int end, int i) {
        if (l <= start && end <= r) {
            return tree[i];
        }
        if (lazy[i] != 0) {
            pushDown(i);
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

    private void pushDown(int i) {
        int lazyNum = this.lazy[i];
        lazy[i] = 0;
        lazy[2 * i] = lazyNum;
        lazy[2 * i + 1] = lazyNum;
        tree[2 * i] = Math.max(tree[2 * i], lazyNum);
        tree[2 * i + 1] = Math.max(tree[2 * i + 1], lazyNum);
    }

}
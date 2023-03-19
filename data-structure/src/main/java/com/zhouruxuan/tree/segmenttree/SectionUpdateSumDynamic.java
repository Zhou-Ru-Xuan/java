package com.zhouruxuan.tree.segmenttree;

/**
 * @author zhouruxuan
 * @date 2022/9/30 08:56
 */
class SectionUpdateSumDynamic {
    class Node {
        int val;
        int lazy;
        boolean updated;
        Node left;
        Node right;
    }

    public Node root;
    public final int START;//范围左边界
    public final int END;//范围右边界

    public SectionUpdateSumDynamic(int start, int end) {
        this.START = start;
        this.END = end;
        root = new Node();
    }

    public void updateSection(int l, int r, int val) {
        updateSection(l, r, val, START, END, root);
    }


    public int querySum(int l, int r) {
        return querySum(l, r, START, END, root);
    }


    private void updateSection(int l, int r, int val, int start, int end, Node root) {
        if (l <= start && end <= r) {
            root.val = (end - start + 1) * val;
            if (start != end) {
                root.updated = true;
                root.lazy = val;
            }
            return;
        }
        addNode(root);
        int mid = start + (end - start) / 2;
        if (root.updated) {
            pushDown(start, mid, end, root);
        }
        if (l <= mid) {
            updateSection(l, r, val, start, mid, root.left);
        }
        if (r > mid) {
            updateSection(l, r, val, mid + 1, end, root.right);
        }
        pushUp(root);
    }


    private int querySum(int l, int r, int start, int end, Node root) {
        if (l <= start && end <= r) {
            return root.val;
        }
        addNode(root);
        int mid = start + (end - start) / 2;
        int leftSum = 0;
        int rightSum = 0;
        if (root.updated) {
            pushDown(start, mid, end, root);
        }
        if (l <= mid) {
            leftSum = querySum(l, r, start, mid, root.left);
        }
        if (r > mid) {
            rightSum = querySum(l, r, mid + 1, end, root.right);
        }
        return leftSum + rightSum;
    }

    public void pushUp(Node root) {
        root.val = root.left.val + root.right.val;
    }

    private void pushDown(int start, int mid, int end, Node root) {
        root.left.lazy = root.lazy;
        root.right.lazy = root.lazy;

        root.left.val = (mid - start + 1) * root.lazy;
        root.right.val = (end - (mid + 1) + 1) * root.lazy;

        root.lazy = 0;

        root.updated = false;
        root.left.updated = true;
        root.right.updated = true;
    }


    private void addNode(Node root) {
        if (root.left == null) root.left = new Node();
        if (root.right == null) root.right = new Node();
    }


}

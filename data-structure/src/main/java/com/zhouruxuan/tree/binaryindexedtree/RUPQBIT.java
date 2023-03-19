package com.zhouruxuan.tree.binaryindexedtree;

class RUPQBIT { // 区间修改单点查询
    int[] diff, tree; // nums为输入数组，diff为差分数组，该数组同基本BIT的tree一样，为一棵或多棵逻辑树
    int n;
    public RUPQBIT(int[] nums){
        this.n = nums.length; // 有效元素个数
        this.diff = new int[n + 1];
        this.tree = new int[n + 1];
        diff[1] = nums[0];
        for(int i = 1; i < n; i++){ // 求diff[]
            diff[i + 1] = nums[i] - nums[i - 1];
        }
        for(int i = 1; i <= n; i++){ // 初始化tree[]
            add(i, diff[i]);
        }
    }
    public void rangeUpdate(int l, int r, int x){ // 区间修改
        add(l, x);
        add(r + 1, -x);
    }
    public int query(int k){ // 单点查询 nums[k]
        int ans = 0;
        for(int i = k; i > 0; i -= lowbit(i)){ // 下一个左邻区间和结点下标为i -= lowbit(i)
            ans += tree[i];
        }
        return ans;
    }
    private void add(int k, int x){ // 为第k个结点tree[k]加上x
        for(int i = k; i <= n; i += lowbit(i)){ // 下一个区间和结点下标为i += lowbit(i)
            tree[i] += x; // 包含第k项的区间都加上x
        }
    }
    private int lowbit(int i){
        return i & -i;
    }
}
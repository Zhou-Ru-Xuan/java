package com.zhouruxuan.unionfind;

import java.util.Arrays;

/**
 * @author zhouruxuan
 * @date 2022/9/29 15:10
 */
class UnionFind {
    /**
     * 并查集的规模
     */
    int size;
    /**
     * 元节点数组，parent[i]：表示第i个节点的元节点为parent[i]
     */
    int[] parent;
    /**
     * 连通分量
     */
    int count;
    /**
     * 节点高度
     */
    int[] height;

    public UnionFind(int n) {
        this.size = n;
        this.count = size;
        this.parent = new int[size];
        this.height = new int[size];
        Arrays.fill(height, 1);
    }

    int find(int key) {
        int temp = key;
        while (temp != parent[temp]) {
            //隔代压缩
            parent[temp] = parent[parent[temp]];
            temp = parent[temp];
        }
        return temp;
    }

    void union(int key1, int key2) {
        int root1 = find(key1);
        int root2 = find(key2);
        if (root1 == root2) {
            return;
        }
        //矮树接到高树上，以此减少高度
        if (height[root1] > height[root2]) {
            parent[root2] = root1;
        } else if (height[root1] < height[root2]) {
            parent[root1] = root2;
        } else {
            parent[root1] = root2;
            root2++;
        }
        parent[root1] = root2;
        count--;
    }

    boolean isUnion(int key1, int key2) {
        return find(key1) == find(key2);
    }

}

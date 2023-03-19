package com.zhouruxuan.utils;

import com.zhouruxuan.entity.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-12
 **/
public class TreeNodeUtils {

    /*
    1.思路分析：
        1.1 序列化是层序遍历的结果，中间会保留null值，直接把null放进去队列里跟着遍历就行。后面多出来的null直接手动清除。
        1.2 有没有更好的办法，因为如果最后一层有很多节点，比如5000个，那么后面有1w个null要手动清除。
        1.3 反序列化，dfs构造就行了吧，因此数组是满二叉树的结构，下标都清楚的。
    2.实现后的反思：
        2.1 不是完全二叉树！不是完全二叉树！不是完全二叉树！一想到完全二叉树就dfs，直接错到底了属于是。
        2.2 序列化的时候，要把右括号添回去，别忘了
     */

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null)
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        LinkedList<TreeNode> deque = new LinkedList();
        deque.addLast(root);
        StringBuilder tempNull = new StringBuilder();
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            if (node == null) {
                //先临时将null放在一起，如果遇到非null的，先把前面的null拼回去再拼当前非null的值。如果不再遇到null，说明已经到底了，这个将没有被用到
                tempNull.append("null,");
            } else {
                sb.append(tempNull).append(node.val).append(",");
                deque.addLast(node.left);
                deque.addLast(node.right);
                tempNull = new StringBuilder();

            }
        }
        //删除最后的逗号
        sb.deleteCharAt(sb.length() - 1);

        //注意别忘了把右括号添上
        return sb + "]";
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("[]"))
            return null;
        data = data.substring(1, data.length() - 1);
        String[] ss = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        ArrayDeque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        int i = 1;
        while (i < ss.length) {
            TreeNode node = deque.pollFirst();
            //left
            if (ss[i].equals("null")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.parseInt(ss[i]));
                deque.addLast(node.left);
            }

            i++;

            //注意没有右子树直接到边界的情况
            if (i >= ss.length)
                break;


            //right
            if (ss[i].equals("null")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.parseInt(ss[i]));
                deque.addLast(node.right);
            }
            i++;
        }

        return root;
    }
}

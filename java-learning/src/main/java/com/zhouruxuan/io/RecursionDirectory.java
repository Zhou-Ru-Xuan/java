package com.zhouruxuan.io;

import java.io.File;

// 递归遍历文件夹下所有的文件
public class RecursionDirectory {
    public static void main(String[] args) {
        File file = new File("java-learning/src/main/java/com/zhouruxuan/io");
        // 调用重构后的方法进行递归遍历
        recursiveTraversal(file);
    }

    public static void recursiveTraversal(File file) {
        // 判断传入的是否是目录
        if (!file.isDirectory()) {
            // 如果不是目录，直接返回
            return;
        }

        // 获取目录下的所有文件和文件夹
        File[] files = file.listFiles();

        // 遍历文件和文件夹
        for (File f : files) {
            if (f.isDirectory()) {
                // 如果是文件夹，递归遍历子目录
                recursiveTraversal(f);
            } else {
                // 如果是文件，打印文件名
                System.out.println(f.getName());
            }
        }
    }

}

package com.zhouruxuan.currency.concurrencyutil;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataContainer {
    private int data;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public int readData() {
        rwLock.readLock().lock(); // 获取读锁
        try {
            // 读取数据
            System.out.println(Thread.currentThread().getName() + " Read data: " + data);
            return data;
        } finally {
            rwLock.readLock().unlock(); // 释放读锁
        }
    }

    public void writeData(int data) {
        rwLock.writeLock().lock(); // 获取写锁
        try {
            // 写入数据
            System.out.println(Thread.currentThread().getName() + " Write data: " + data);
            this.data = data;
        } finally {
            rwLock.writeLock().unlock(); // 释放写锁
        }
    }

    @Test
    public void test() {
        DataContainer container = new DataContainer();

        // 创建两个读线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    container.readData();
                }
            }, "ReadThread-" + i).start();
        }

        // 创建两个写线程
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    container.writeData(finalI);
                }
            }, "WriteThread-" + i).start();
        }
    }
}

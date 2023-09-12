package com.zhouruxuan.io;

import org.junit.Test;

import java.io.*;

public class BufferedInputOutputStreamTest {
    @Test
    public void testNoBuffered() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 创建流对象
        try (
                FileInputStream fis = new FileInputStream(Constant.RelativePathInTest + "/cat.jpeg");
                FileOutputStream fos = new FileOutputStream(Constant.RelativePathInTest + "/cat-no-buffer-copy.jpeg")
        ) {
            // 读写数据
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms"); // 4401ms
    }

    @Test
    public void testNoBufferedWithByteArray() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 创建流对象
        try (
                FileInputStream fis = new FileInputStream(Constant.RelativePathInTest + "/cat.jpeg");
                FileOutputStream fos = new FileOutputStream(Constant.RelativePathInTest + "/cat-no-buffer-copy-with-byte-array.jpeg")
        ) {
            // 手动创建缓冲区，理论上越大越快
            byte[] buffer = new byte[10];
            int len;
            // 读写数据
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms"); // 476ms
    }

    @Test
    public void testWithBuffered() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 创建流对象
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(Constant.RelativePathInTest + "/cat.jpeg"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Constant.RelativePathInTest + "/cat-buffed-copy.jpeg"));
        ) {
            // 读写数据
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms"); // 41ms
    }

    @Test
    public void testWithBufferedAndByteArray() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 创建流对象
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(Constant.RelativePathInTest + "/cat.jpeg"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(Constant.RelativePathInTest + "/cat-buffed-copy-with-byte-array.jpeg"));
        ) {
            // 读写数据
            int len;
            byte[] bytes = new byte[10];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms"); // 12ms
    }

}

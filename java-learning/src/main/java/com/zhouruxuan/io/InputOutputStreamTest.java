package com.zhouruxuan.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutputStreamTest {
    @Test
    public void testNewLine() {
        try (FileOutputStream fos = new FileOutputStream(Constant.RelativePathInTest + "/2.txt", true)) {
            // 在文件末尾添加换行符
            fos.write(System.lineSeparator().getBytes());
            // 写入字符'b'
            fos.write('b');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCopyPhoto() {
        // 指定数据源和目的地
        String sourcePath = Constant.RelativePathInTest + "/cat.jpeg";
        String destPath = Constant.RelativePathInTest + "/cat-copy.jpeg";

        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destPath)) {

            // 定义缓冲区
            byte[] buffer = new byte[1024];
            int bytesRead;

            // 循环读取并写入数据
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

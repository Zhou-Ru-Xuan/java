package com.zhouruxuan.io;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriterTest {

    @Test
    public void testNoClose() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(Constant.RelativePathInTest + "/4.txt");
        // 将数据写入缓冲区
        fw.write('刷'); // 写出第1个字符
        // 将数据从缓冲区写入文件，并关闭IO流
        fw.close();

        fw.write("新");// java.io.IOException: Stream closed
        fw.close();
    }

    @Test
    public void testFlush() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(Constant.RelativePathInTest + "/3.txt");
        // 将数据写入缓冲区
        fw.write('刷'); // 写出第1个字符
        // 将数据从缓冲区写入文件
        fw.flush();

        fw.write("新");// java.io.IOException: Stream closed
        fw.close();
    }
}

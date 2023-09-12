package com.zhouruxuan.io;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.zhouruxuan.io.Constant.AbsolutePath;
import static com.zhouruxuan.io.Constant.RelativePathInTest;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-20
 **/
public class FileTest {
    @Test
    public void test1() {
        System.out.println(System.getProperty("user.dir"));
        File file = new File(AbsolutePath + "/1.txt");
        File file2 = new File(AbsolutePath + "/2.txt");
        try (FileOutputStream fos = new FileOutputStream(file2)) {
            fos.write(97);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file3 = new File(AbsolutePath + "/3.txt");
        try (FileOutputStream fos = new FileOutputStream(file3)) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test2() throws FileNotFoundException {
        File file = new File(RelativePathInTest + "/4.txt");
        FileOutputStream fos = new FileOutputStream(file);
    }

    @Test
    public void test3() throws FileNotFoundException {
        //java.io.FileNotFoundException: src/main/java/com/zhouruxuan/io/dir1/5.txt (No such file or directory)。因为没有dir1
        File file = new File(RelativePathInTest + "/dir1/5.txt");
        FileOutputStream fos = new FileOutputStream(file);
    }

    @Test
    public void test4() throws FileNotFoundException {
        //java.io.FileNotFoundException: /dir1/6.txt (No such file or directory)。因为没有dir1
        FileOutputStream fos = new FileOutputStream("/dir1/6.txt");
    }
}

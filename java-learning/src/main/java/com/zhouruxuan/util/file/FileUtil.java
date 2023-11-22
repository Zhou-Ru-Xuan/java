package com.zhouruxuan.util.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    /**
     * 根据路径获取文件流
     *
     * @param filePath
     * @return
     */
    public static InputStream readFile(String filePath) {
        try {
            return Files.newInputStream(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取文件并逐行打印
     *
     * @param filePath
     */
    public static void readAndPrint(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null && lineCount < 2) {
                System.out.println(line);
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

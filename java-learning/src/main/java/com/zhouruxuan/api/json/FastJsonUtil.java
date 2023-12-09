package com.zhouruxuan.api.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FastJsonUtil {
    public static String readJsonFile(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

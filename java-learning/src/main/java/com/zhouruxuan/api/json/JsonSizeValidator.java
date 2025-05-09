package com.zhouruxuan.api.json;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSizeValidator {

    public static void main(String[] args) {
        try {
            String filePath = "/Users/zhouruxuan/Documents/code/java/java/java-learning/src/main/java/com/zhouruxuan/api/json/data.json"; // 替换为你的文件路径
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            
            // 计算字节长度
            byte[] bytes = jsonContent.getBytes(StandardCharsets.UTF_8);
            System.out.println("JSON长度检测结果：");
            System.out.println("字符数：" + jsonContent.length());
            System.out.println("字节数：" + bytes.length);
            System.out.println("是否超过TEXT类型限制：" + (bytes.length > 65535));
            
            // 阈值提醒
            if (bytes.length > 16777215) {
                System.err.println("警告：超过MEDIUMTEXT限制！");
            } else if (bytes.length > 65535) {
                System.out.println("建议：请使用MEDIUMTEXT类型");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.zhouruxuan.api.compress;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GzipCompressionRatioTest {

    // 测试不同长度的压缩率
    @Test
    void testCompressionRatioTrend() throws Exception {
        Map<Integer, Double> ratioResults = new LinkedHashMap<>();

        // 测试不同长度的字符串（单位：字节）
        int[] testLengths = {10, 50, 100, 500, 1000, 5000, 10000};

        for (int length : testLengths) {
            String data = generateCompressibleData(length);
            byte[] original = data.getBytes(StandardCharsets.UTF_8);
            byte[] compressed = GzipCompressionUtil.compress(data);
            
            double ratio = (double) compressed.length / original.length;
            ratioResults.put(length, ratio);
        }

        // 输出压缩率表格
        printRatioTable(ratioResults);

        // 验证趋势：长数据的压缩率应明显优于短数据
        assertAll(
            () -> assertTrue(ratioResults.get(10000) < ratioResults.get(100),
                "10000字节的压缩率应优于100字节"),
            () -> assertTrue(ratioResults.get(1000) < ratioResults.get(50),
                "1000字节的压缩率应优于50字节"),
            () -> assertTrue(ratioResults.get(500) < 0.8,
                "500字节数据的压缩率应低于80%"),
            () -> assertTrue(ratioResults.get(10000) < 0.3,
                "10000字节数据的压缩率应低于30%")
        );
    }

    // 生成可压缩的测试数据（包含重复模式）
    private String generateCompressibleData(int targetLength) {
        StringBuilder sb = new StringBuilder();
        String pattern = "{\"id\":%d,\"name\":\"user%d\",\"active\":true},";
        
        while (sb.length() < targetLength) {
            int num = (int)(Math.random() * 1000);
            sb.append(String.format(pattern, num, num));
        }
        
        return sb.substring(0, targetLength);
    }

    // 打印压缩率表格
    private void printRatioTable(Map<Integer, Double> results) {
        System.out.println("\n压缩率测试结果：");
        System.out.println("+-----------+------------+----------------+");
        System.out.println("| 原始长度  | 压缩后长度 | 压缩率 (%)     |");
        System.out.println("+-----------+------------+----------------+");
        
        results.forEach((length, ratio) -> {
            String compressedLength = String.valueOf((int)(length * ratio));
            String ratioPercent = String.format("%.1f", ratio * 100);
            
            System.out.printf("| %-9d | %-10s | %-14s |\n", 
                length, compressedLength, ratioPercent + "%");
        });
        
        System.out.println("+-----------+------------+----------------+");
    }

    // 验证小数据压缩率问题
    @Test
    void testSmallDataCompressionOverhead() throws Exception {
        String tinyData = "a";
        byte[] original = tinyData.getBytes(StandardCharsets.UTF_8);
        byte[] compressed = GzipCompressionUtil.compress(tinyData);
        
        System.out.printf("\n小数据测试：原始长度=%d 压缩后=%d\n", 
            original.length, compressed.length);
        
        assertTrue(compressed.length > original.length, 
            "极小数据压缩后体积应增大");
    }
}

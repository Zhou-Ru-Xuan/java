package com.zhouruxuan.api.compress;

import com.github.luben.zstd.Zstd;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZstdCompressionRatioTest {

    // 测试不同数据长度的压缩率
    @Test
    void testZstdCompressionRatioTrend() {
        Map<Integer, Double> ratioResults = new LinkedHashMap<>();
        int[] testLengths = {10, 50, 100, 200, 500, 1000, 5000, 10000};

        for (int length : testLengths) {
            String data = generateTestData(length);
            byte[] original = data.getBytes(StandardCharsets.UTF_8);
            byte[] compressed = Zstd.compress(original,3);

            double ratio = (double) compressed.length / original.length;
            ratioResults.put(length, ratio);
        }

        printRatioTable(ratioResults);
        verifyCompressionTrend(ratioResults);
    }

    // 生成可压缩的测试数据（带重复模式）
    private String generateTestData(int targetLength) {
        StringBuilder sb = new StringBuilder();
        String template = "{\"id\":%d,\"value\":%.2f},";
        int counter = 0;

        while (sb.length() < targetLength) {
            String record = String.format(template, counter, Math.random() * 1000);
            sb.append(record);
            counter++;
        }
        return sb.substring(0, targetLength);
    }

    // 打印压缩率表格
    private void printRatioTable(Map<Integer, Double> results) {
        System.out.println("\nZstd压缩率测试结果：");
        System.out.println("+-----------+------------+----------------+");
        System.out.println("| 原始长度  | 压缩后长度 | 压缩率 (%)     |");
        System.out.println("+-----------+------------+----------------+");

        results.forEach((length, ratio) -> {
            int compressedLength = (int) (length * ratio);
            System.out.printf("| %-9d | %-10d | %-14.1f |\n",
                length, compressedLength, ratio * 100);
        });
        System.out.println("+-----------+------------+----------------+");
    }

    // 验证压缩率趋势
    private void verifyCompressionTrend(Map<Integer, Double> results) {
        // 验证小数据膨胀现象
        assertTrue(results.get(10) > 1.0,
            "10字节数据压缩后应体积增大，实际：" + results.get(10));
        assertTrue(results.get(50) > 0.9,
            "50字节数据压缩率应较差，实际：" + results.get(50));

        // 验证长数据优化效果
        assertTrue(results.get(1000) < 0.4,
            "1000字节数据压缩率应<40%，实际：" + results.get(1000));
        assertTrue(results.get(10000) < 0.3,
            "10000字节数据压缩率应<30%，实际：" + results.get(10000));

        // 验证整体趋势
        double previousRatio = 2.0;
        for (int length : new int[]{10, 50, 100, 200, 500, 1000, 5000, 10000}) {
            double currentRatio = results.get(length);
            assertTrue(currentRatio <= previousRatio,
                String.format("压缩率应逐步优化：%d字节(%.1f%%) → %d字节(%.1f%%)违反趋势",
                    length/2, previousRatio*100, length, currentRatio*100));
            previousRatio = currentRatio;
        }
    }

    // 小数据专项测试
    @Test
    void testSmallDataOverhead() {
        String tinyData = "a"; // 1字节数据
        byte[] original = tinyData.getBytes(StandardCharsets.UTF_8);
        byte[] compressed = Zstd.compress(original);

        System.out.printf("\n小数据测试：原始长度=%d 压缩后=%d\n",
            original.length, compressed.length);

        assertTrue(compressed.length > original.length,
            "极小数据压缩后体积应增大，实际：" + compressed.length);
    }

    // 数据完整性验证
    @Test
    void testDataIntegrity() throws Exception {
        String data = generateTestData(1000);
        byte[] original = data.getBytes(StandardCharsets.UTF_8);

        byte[] compressed = Zstd.compress(original);
        byte[] decompressed = Zstd.decompress(compressed, original.length);

        assertArrayEquals(original, decompressed,
            "解压后数据应与原始数据完全一致");
    }
}

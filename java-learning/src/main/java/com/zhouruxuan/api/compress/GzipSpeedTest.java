package com.zhouruxuan.api.compress;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GzipSpeedTest {

    // 测试数据长度配置（单位：字节）
    private static final int[] TEST_LENGTHS = {
        10,         // 极小数据
        100,        // 小数据
        1_000,      // 中等数据
        10_000,     // 中大数据
        100_000,    // 大数据
        1_000_000   // 超大数据
    };

    // 预热次数（避免JIT影响）
    private static final int WARMUP_ITERATIONS = 5;
    // 正式测试次数
    private static final int TEST_ITERATIONS = 10;

    @Test
    void testCompressionSpeedTrend() throws Exception {
        System.out.println("Gzip压缩速度测试报告");
        System.out.println("==========================================");

        // 存储测试结果 <数据长度, 平均压缩时间(纳秒)>
        long[] results = new long[TEST_LENGTHS.length];

        // 执行预热
        warmupJvm();

        // 正式测试
        for (int i = 0; i < TEST_LENGTHS.length; i++) {
            int length = TEST_LENGTHS[i];
            String data = generateData(length);
            
            long totalTime = 0;
            for (int j = 0; j < TEST_ITERATIONS; j++) {
                totalTime += measureCompressionTime(data);
            }
            
            long avgTime = totalTime / TEST_ITERATIONS;
            results[i] = avgTime;
            
            printResult(length, avgTime);
        }

        // 验证趋势
        verifySpeedTrend(results);
    }

    // 生成可压缩的测试数据
    private String generateData(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random(42); // 固定种子保证可重复性
        
        // 生成包含重复模式的JSON数据
        while (sb.length() < length) {
            sb.append(String.format("{\"id\":%d,\"val\":%.2f},",
                    random.nextInt(1000),
                    random.nextDouble() * 1000));
        }
        return sb.substring(0, length);
    }

    // 测量单次压缩时间（单位：纳秒）
    private long measureCompressionTime(String data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        long start = System.nanoTime();
        
        try (GZIPOutputStream gzos = new GZIPOutputStream(bos)) {
            gzos.write(data.getBytes());
        }
        
        return System.nanoTime() - start;
    }

    // JVM预热
    private void warmupJvm() throws Exception {
        String warmupData = generateData(10_000);
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            measureCompressionTime(warmupData);
        }
    }

    // 打印测试结果
    private void printResult(int length, long avgTime) {
        double ms = avgTime / 1_000_000.0;
        double speed = (length / (1024.0 * 1024)) / (ms / 1000); // MB/s
        
        System.out.printf("数据长度：%,8d bytes | 平均耗时：%6.2f ms | 吞吐量：%6.2f MB/s%n",
                length, ms, speed);
    }

    // 验证速度趋势
    private void verifySpeedTrend(long[] results) {
        System.out.println("\n趋势验证：");
        
        for (int i = 1; i < results.length; i++) {
            long prev = results[i-1];
            long current = results[i];
            
            // 验证耗时增长比例
            double growthFactor = (double)current / prev;
            int length = TEST_LENGTHS[i];
            
            System.out.printf("长度 %,8d → %,8d : 耗时增长 %.1f 倍%n",
                    TEST_LENGTHS[i-1], length, growthFactor);
            
            // 基本断言（可根据实际情况调整阈值）
            if (length >= 10_000) {
                assertTrue(growthFactor > 1.5,
                        "大数据量应呈现明显耗时增长，实际增长倍数：" + growthFactor);
            }
        }
    }
}

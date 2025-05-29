package com.zhouruxuan.api.compress;

import com.github.luben.zstd.Zstd;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class ZstdSpeedTest {

    // 测试数据长度配置（单位：字节，10的倍数）
    private static final int[] TEST_LENGTHS = {
        10,         // 极小数据
        100,        // 小数据
        1_000,      // 1KB（十进制）
        10_000,     // 10KB
        100_000,    // 100KB
        1_000_000   // 1MB（十进制）
    };

    // 测试参数
    private static final int WARMUP_ITERATIONS = 10;
    private static final int TEST_ITERATIONS = 20;
    private static final int ZSTD_LEVEL = 3;

    @Test
    void testZstdSpeedTrend() throws Exception {
        System.out.println("Zstd压缩速度测试报告（十进制数据长度）");
        System.out.println("==========================================");
        
        // 预热JVM
        warmupJvm();

        // 执行测试
        long[] results = new long[TEST_LENGTHS.length];
        for (int i = 0; i < TEST_LENGTHS.length; i++) {
            int length = TEST_LENGTHS[i];
            byte[] data = generateData(length);
            
            long totalTime = 0;
            for (int j = 0; j < TEST_ITERATIONS; j++) {
                totalTime += measureCompressionTime(data);
            }
            
            results[i] = totalTime / TEST_ITERATIONS;
            printResult(length, results[i]);
        }

        // 验证趋势
        verifySpeedTrend(results);
    }

    // 生成可压缩的测试数据
    private byte[] generateData(int length) {
        Random random = new Random(42);
        StringBuilder sb = new StringBuilder();
        
        // 生成结构化数据（CSV格式）
        while (sb.length() < length) {
            sb.append(String.format("%d,%.3f,%d,",
                    random.nextInt(1000),
                    random.nextDouble() * 100,
                    System.currentTimeMillis()));
        }
        
        byte[] data = sb.toString().getBytes();
        return Arrays.copyOf(data, length); // 精确长度控制
    }

    // 测量单次压缩时间（纳秒）
    private long measureCompressionTime(byte[] data) {
        long start = System.nanoTime();
        Zstd.compress(data, ZSTD_LEVEL);
        return System.nanoTime() - start;
    }

    // JVM预热
    private void warmupJvm() {
        byte[] warmupData = generateData(100_000);
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Zstd.compress(warmupData, ZSTD_LEVEL);
        }
    }

    // 打印结果
    private void printResult(int length, long avgTime) {
        double ms = avgTime / 1_000_000.0;
        double throughput = (length / 1_000_000.0) / (ms / 1000); // MB/s（十进制）
        
        System.out.printf("长度：%,8d bytes | 平均耗时：%6.3f ms | 吞吐量：%7.2f MB/s%n",
                length, ms, throughput);
    }

    // 验证速度趋势
    private void verifySpeedTrend(long[] results) {
        System.out.println("\n趋势验证（每次数据量增长10倍）：");
        
        for (int i = 1; i < TEST_LENGTHS.length; i++) {
            int prevLength = TEST_LENGTHS[i-1];
            int currentLength = TEST_LENGTHS[i];
            long prevTime = results[i-1];
            long currentTime = results[i];
            
            double timeGrowth = (double) currentTime / prevTime;
            
            System.out.printf("%6d → %7d bytes | 耗时增长: %5.1f倍%n",
                    prevLength, currentLength, timeGrowth);
        }
    }
}

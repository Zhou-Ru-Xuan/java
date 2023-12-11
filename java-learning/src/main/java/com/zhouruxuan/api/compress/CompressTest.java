package com.zhouruxuan.api.compress;

import com.zhouruxuan.api.json.FastJsonUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class CompressTest {
    private String jsonStr = FastJsonUtil.readResourceJsonFile("/json/mtaGoodsList.json");

    @Test
    public void testGzip() throws IOException {
        Instant startTime = Instant.now();
        String compressedString = CompressUtil.GzipCompress(jsonStr);
        Instant endTime = Instant.now();
        String decompress = CompressUtil.GzipDecompress(compressedString);
        Instant endTime2 = Instant.now();

        System.out.println("rate:" + (double) compressedString.length() / jsonStr.length());
        Duration executionTime = Duration.between(startTime, endTime);
        Duration executionTime2 = Duration.between(endTime, endTime2);

        System.out.println("压缩：" + executionTime.toMillis() + "毫秒");
        System.out.println("解压：" + executionTime2.toMillis() + "毫秒");

    }

    @Test
    public void testZstd() throws IOException {
        Instant startTime = Instant.now();
        String compressedString = CompressUtil.ZstdCompress(jsonStr);
        Instant endTime = Instant.now();
        String decompress = CompressUtil.ZstdDecompress(compressedString);
        Instant endTime2 = Instant.now();
        System.out.println("rate:" + (double) compressedString.length() / jsonStr.length());
        Duration executionTime = Duration.between(startTime, endTime);
        Duration executionTime2 = Duration.between(endTime, endTime2);
        System.out.println("压缩：" + executionTime.toMillis() + "毫秒");
        System.out.println("解压：" + executionTime2.toMillis() + "毫秒");
    }
}

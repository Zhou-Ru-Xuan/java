package com.zhouruxuan.api.compress;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressionUtilTest {

    @Test
    public void testCompressionCycle() throws Exception {
        // 处理长文本特殊标记

        String original = generateRepeatedText("SampleText", 100000);


        byte[] compressed = GzipCompressionUtil.compress(original);
        String decompressed = GzipCompressionUtil.decompress(compressed);

        final String originalStr = original;

        assertAll(
                () -> assertNotEquals(originalStr, new String(compressed),
                        "Compressed data should differ from original"),
                () -> assertEquals(originalStr, decompressed,
                        "Decompressed data should match original"),
                () -> assertTrue(compressed.length < originalStr.getBytes(StandardCharsets.UTF_8).length,
                        "Compressed size should be smaller. Original: " +
                                originalStr.getBytes(StandardCharsets.UTF_8).length +
                                " Compressed: " + compressed.length)
        );
    }

    // 空字符串测试
    @Test
    void testEmptyString() throws Exception {
        String empty = "";
        byte[] compressed = GzipCompressionUtil.compress(empty);
        String decompressed = GzipCompressionUtil.decompress(compressed);

        assertEquals(empty, decompressed, "Empty string should roundtrip correctly");
        assertTrue(compressed.length > 10,
                "GZIP header should exist. Actual length: " + compressed.length);
    }

    // 异常情况测试
    @Test
    void testInvalidDataDecompression() {
        byte[] invalidData = "Invalid compressed data".getBytes(StandardCharsets.UTF_8);

        Exception ex = assertThrows(Exception.class,
                () -> GzipCompressionUtil.decompress(invalidData),
                "Should throw on invalid data");

        assertTrue(ex.getMessage().contains("Not in GZIP format"),
                "Exception message should indicate format problem");
    }

    // 性能验证测试
    @Test
    void testCompressionEfficiency() throws Exception {
        String data = generateTestData(1024 * 1024); // 1MB数据
        byte[] originalBytes = data.getBytes(StandardCharsets.UTF_8);

        byte[] compressed = GzipCompressionUtil.compress(data);
        double ratio = (double) compressed.length / originalBytes.length;

        assertAll(
                () -> assertTrue(ratio < 0.3,
                        "Compression ratio should be <30%. Actual: " + (ratio * 100) + "%"),
                () -> assertTrue(compressed.length > 0,
                        "Compressed data should not be empty")
        );
    }

    // 二进制数据测试
    @Test
    void testBinaryDataRoundtrip() throws Exception {
        byte[] binaryData = new byte[256];
        for (int i = 0; i < 256; i++) {
            binaryData[i] = (byte) i;
        }
        String original = new String(binaryData, StandardCharsets.ISO_8859_1);

        byte[] compressed = GzipCompressionUtil.compress(original);
        String decompressed = GzipCompressionUtil.decompress(compressed);

        assertArrayEquals(binaryData, decompressed.getBytes(StandardCharsets.ISO_8859_1),
                "Binary data should roundtrip exactly");
    }

    // 辅助方法：生成重复文本
    private String generateRepeatedText(String base, int repetitions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            sb.append(base);
        }
        return sb.toString();
    }

    // 辅助方法：生成测试数据
    private String generateTestData(int targetSize) {
        StringBuilder sb = new StringBuilder();
        String seed = System.currentTimeMillis() + "|";
        while (sb.length() < targetSize) {
            sb.append(seed);
        }
        return sb.substring(0, targetSize);
    }
}

package com.zhouruxuan.api.compress;

import com.github.luben.zstd.Zstd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressUtil {
    public static String GzipCompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes());
        }
        byte[] compressedBytes = out.toByteArray();
        return Base64.getEncoder().encodeToString(compressedBytes);
    }

    public static String GzipDecompress(String compressedStr) throws IOException {
        if (compressedStr == null || compressedStr.length() == 0) {
            return compressedStr;
        }
        byte[] compressedBytes = Base64.getDecoder().decode(compressedStr);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressedBytes))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzip.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
        return out.toString();
    }

    public static String ZstdCompress(String input) {
        byte[] inputData = input.getBytes(StandardCharsets.UTF_8);
        byte[] compressedData = Zstd.compress(inputData);
        return Base64.getEncoder().encodeToString(compressedData);
    }

    public static String ZstdDecompress(String compressedInput) {
        byte[] compressedData = Base64.getDecoder().decode(compressedInput);
        long decompressedSize = Zstd.getFrameContentSize(compressedData);
        byte[] decompressedData = new byte[(int) decompressedSize];
        Zstd.decompress(compressedData, decompressedData);
        return new String(decompressedData, StandardCharsets.UTF_8);
    }
}

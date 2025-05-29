package com.zhouruxuan.api.compress;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;

public class GzipCompressionUtil {
    
    // 压缩方法
    public static byte[] compress(String data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
            gzipOS.write(data.getBytes(StandardCharsets.UTF_8));
        }
        return bos.toByteArray();
    }

    // 解压方法
    public static String decompress(byte[] compressedData) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPInputStream gzipIS = new GZIPInputStream(bis)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipIS.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        }
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }
}

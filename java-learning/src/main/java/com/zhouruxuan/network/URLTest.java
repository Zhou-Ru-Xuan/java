package com.zhouruxuan.network;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    @Test
    public void testUrl() {
        URL url = null;
        try {
            url = new URL("http://www.itmind.net/category/payment-selection/zhishixingqiu-jingxuan/");
            System.out.println("host: " + url.getHost());
            System.out.println("port: " + url.getPort());
            System.out.println("uri_path: " + url.getPath());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testConnection() {
        try {
            URL url = new URL("http://www.itmind.net");
            URLConnection connection = url.openConnection();
            System.out.println(connection.getContentType());
            System.out.println(connection.getContentLength());
            System.out.println(connection.getContentEncoding());
            System.out.println(connection.getDate());
            System.out.println(connection.getExpiration());
            System.out.println(connection.getLastModified());

            String result = readFromConnection(connection);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从URLConnection读取数据
     *
     * @param connection URLConnection对象
     * @return 读取的数据
     * @throws IOException
     */
    private String readFromConnection(URLConnection connection) throws IOException {
        try (InputStream in = connection.getInputStream();
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            return output.toString();
        }
    }

}

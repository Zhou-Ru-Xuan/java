package com.zhouruxuan.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1",8899);
             OutputStream os = socket.getOutputStream()) {
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
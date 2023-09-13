package com.zhouruxuan.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try (
                // 1.创建服务器端的ServerSocket，指明自己的端口号
                ServerSocket ss = new ServerSocket(8899);
                // 2.调用accept()表示接收来自于客户端的socket
                Socket socket = ss.accept();
                // 3.获取输入流
                InputStream is = socket.getInputStream();
                // 4.读取输入流中的数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            System.out.println(baos);
            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
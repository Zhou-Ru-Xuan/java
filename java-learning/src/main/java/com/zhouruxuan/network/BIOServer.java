package com.zhouruxuan.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建serverSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端已启动，端口号为8888...");
        while (true) {
            System.out.println("线程的信息 id=" + Thread.currentThread().getId() + " 名称=" + Thread.currentThread().getName());
            System.out.println("等待连接...");
            // 监听
            final Socket accept = serverSocket.accept();
            System.out.println("有一个客户端连接...");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            // 获取输入流
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("线程的信息 id=" + Thread.currentThread().getId() + " 名称=" + Thread.currentThread().getName());
                System.out.println("read...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

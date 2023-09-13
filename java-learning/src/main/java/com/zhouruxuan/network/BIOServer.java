package com.zhouruxuan.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    private static final int PORT = 8888;
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("服务端已启动，端口号为" + PORT + "...");
            while (true) {
                acceptClient(serverSocket);
            }
        }
    }

    private static void acceptClient(ServerSocket serverSocket) throws IOException {
        System.out.println("等待连接...");
        final Socket clientSocket = serverSocket.accept();
        System.out.println("有一个客户端连接...");
        executorService.execute(() -> handler(clientSocket));
    }

    public static void handler(Socket socket) {
        try (InputStream inputStream = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
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
        }
    }


}

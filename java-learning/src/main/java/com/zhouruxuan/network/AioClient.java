package com.zhouruxuan.network;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class AioClient {

    // 服务端IP地址
    private static final String SERVER_IP = "127.0.0.1";
    // 服务端端口号
    private static final int SERVER_PORT = 9988;
    // 客户端发送的消息
    private static final String MESSAGE = "Hello,我是客户端";

    public static void main(String[] args) {
        try {
            // 建立与服务端的连接
            AsynchronousSocketChannel channel = establishConnection();
            // 向服务端发送数据
            sendData(channel, MESSAGE);
            // 接收服务端返回的数据
            String result = receiveData(channel);
            System.out.println("客户端收到服务端返回的内容：" + result);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立与服务端的连接
     * @return 连接通道
     * @throws IOException
     * @throws InterruptedException
     */
    private static AsynchronousSocketChannel establishConnection() throws IOException, InterruptedException {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
        Thread.sleep(1000);
        return channel;
    }

    /**
     * 向服务端发送数据
     * @param channel 连接通道
     * @param message 发送的消息
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void sendData(AsynchronousSocketChannel channel, String message) throws ExecutionException, InterruptedException {
        channel.write(ByteBuffer.wrap(message.getBytes())).get();
    }

    /**
     * 接收服务端返回的数据
     * @param channel 连接通道
     * @return 服务端返回的数据
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     */
    private static String receiveData(AsynchronousSocketChannel channel) throws ExecutionException, InterruptedException, IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer).get();
        byteBuffer.flip();
        return Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
    }
}

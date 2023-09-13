package com.zhouruxuan.network;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class AioServer {

    private AsynchronousServerSocketChannel serverSocketChannel;

    // 开始监听
    public void listen() throws Exception {
        // 打开一个服务端通道
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        // 绑定9988端口
        serverSocketChannel.bind(new InetSocketAddress(9988));
        // 接收客户端连接
        serverSocketChannel.accept(this, new AcceptHandler());
    }

    // 客户端连接处理器
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
        @Override
        public void completed(AsynchronousSocketChannel client, AioServer attachment) {
            try {
                if (client.isOpen()) {
                    System.out.println("接收到新的客户端的连接，地址：" + client.getRemoteAddress());
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // 读取客户端发送的数据
                    client.read(byteBuffer, client, new ReadHandler(byteBuffer));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 当有新的客户端接入的时候，直接调用accept的方法，递归执行下去，这样可以保证多个客户端都可以阻塞
                attachment.serverSocketChannel.accept(attachment, this);
            }
        }

        @Override
        public void failed(Throwable exc, AioServer attachment) {
            exc.printStackTrace();
        }
    }

    // 读取数据处理器
    private class ReadHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private ByteBuffer byteBuffer;

        public ReadHandler(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel client) {
            try {
                byteBuffer.flip();
                String content = Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
                System.out.println("服务端接受到客户端发来的数据：" + content);
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put("Server send".getBytes());
                writeBuffer.flip();
                // 向客户端发送数据
                client.write(writeBuffer).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel client) {
            try {
                exc.printStackTrace();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new AioServer().listen();
        Thread.sleep(Integer.MAX_VALUE);
    }
}

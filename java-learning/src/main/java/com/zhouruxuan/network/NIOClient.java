package com.zhouruxuan.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO客户端
 */
public class NIOClient {
    // 服务器IP地址
    private static final String SERVER_IP = "127.0.0.1";
    // 服务器端口号
    private static final int SERVER_PORT = 8888;
    // 发送给服务器的消息
    private static final String MESSAGE = "Hello Server!";
    // 选择器，用于处理所有的网络连接
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        // 初始化客户端
        initClient();
        // 处理与服务器的通信
        handleCommunication();
    }

    // 初始化客户端
    private static void initClient() throws IOException {
        // 打开选择器
        selector = Selector.open();
        // 打开通道
        SocketChannel channel = SocketChannel.open();
        // 设置为非阻塞模式
        channel.configureBlocking(false);
        // 连接到服务器
        channel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
        // 注册连接事件
        channel.register(selector, SelectionKey.OP_CONNECT);

    }

    // 处理与服务器的通信
    private static void handleCommunication() throws IOException {
        while (true) { // 不断地选择准备好的通道进行I/O操作
            // 选择一组键，其相应的通道已为 I/O 操作准备就绪
            selector.select();
            // 获取选择键的迭代器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) { // 遍历所有的选择键
                SelectionKey key = iterator.next();
                // 删除当前的选择键
                iterator.remove();
                // 如果当前的选择键为连接事件
                if (key.isConnectable()) {
                    // 连接到服务器
                    connectToServer(key);
                }
                // 如果当前的选择键为读事件
                else if (key.isReadable()) {
                    // 读取服务器发送的消息
                    readMessage(key);
                }
            }
        }
    }

    // 连接到服务器
    private static void connectToServer(SelectionKey key) throws IOException {
        // 获取通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 如果连接还未完成，则完成连接
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        // 设置为非阻塞模式
        channel.configureBlocking(false);
        // 向服务器发送消息
        channel.write(ByteBuffer.wrap(MESSAGE.getBytes()));
        // 注册读事件
        channel.register(selector, SelectionKey.OP_READ);
    }

    // 读取服务器发送的消息
    private static void readMessage(SelectionKey key) throws IOException {
        // 获取通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(128);
        // 读取数据
        channel.read(buffer);
        // 将数据转换为字符串
        String msg = new String(buffer.array()).trim();
        // 打印消息
        System.out.println("服务端发来的消息：" + msg);
    }

}


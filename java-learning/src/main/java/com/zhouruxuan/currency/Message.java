package com.zhouruxuan.currency;

import java.util.Arrays;

public class Message {
    private static final int MAX_LENGTH = 256;
    private static char[] msg = new char[MAX_LENGTH];

    public static char[] readMsg() {
        return Arrays.copyOf(msg, msg.length);
    }

    public static void writeMsg(char[] newMsg) {
        int len = Math.min(newMsg.length, MAX_LENGTH);
        System.arraycopy(newMsg, 0, msg, 0, len);
    }

    public static void thread1() {
        char[] newMsg = "this is new msg, it's too looooooong".toCharArray();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writeMsg(newMsg);
    }

    public static void thread2() {
        System.out.println("msg=" + new String(readMsg()));
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(Message::thread1);
        Thread t2 = new Thread(Message::thread2);
        t1.start();
        t2.start();
    }
}

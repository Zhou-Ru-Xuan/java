package com.zhouruxuan.currency.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * demo
 * 线程池中,给不同的用户发送大象消息
 * 修复方案,修饰TtlRunnable
 */
public class TTLCorrectTest {
  	//把InheritableThreadLocal升级为TransmittableThreadLocal
    private static final ThreadLocal<User> resources = new TransmittableThreadLocal();

    private static List<User> userList;

    static {
        userList = new ArrayList<>(10);
        //从定时任务中模拟获取到了10个用户数据,id分别是0到9
        IntStream.range(0, 10).forEach(i -> userList.add(new User(i, "userName" + i)));
    }

    private static ExecutorService executorService = new ThreadPoolExecutor(
            5,
            5,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100));

    public static void main(String[] args) {
        //定时任务,不同的用户发短信通知
        userList.forEach(user -> {
            //模拟往ThreadLocal中放入用户信息
            resources.set(user);
            System.out.println("主线程放入用户信息: " + user.toString());

            //(...)中间执行很多业务逻辑

          	//修饰TtlRunnable
            executorService.submit(Objects.requireNonNull(TtlRunnable.get(() -> {
                //异步发送大象消息,通知用户
                sendXMMessage("简历变更请知晓");
            })));
            System.out.println("主线程操作结束");
            resources.remove();
        });
    }

    private static void sendXMMessage(String action) {
        //从ThreadLocal中获取用户信息
        User user = resources.get();
        if (Objects.nonNull(user)) {
            System.out.println(MessageFormat.format("----子线程发送大象消息currentUser: {0}, action: {1}", user.toString(), action));
        } else {
            System.out.println(MessageFormat.format("----子线程发送大象消息, 用户为空, action: {0}", action));
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }
}
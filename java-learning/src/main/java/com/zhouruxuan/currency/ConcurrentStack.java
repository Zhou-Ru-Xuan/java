package com.zhouruxuan.currency;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<T> {
    private static class Node<T> {
        final T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T data) {
        Node<T> new_node = new Node<>(data);//首先创建一个新的节点，并将其next指针指向当前的头部节点。
        new_node.next = head.get();
        //然后使用compareAndSet()方法将新节点设置为新的头部节点。
        //compareAndSet()方法是一个原子操作，它会比较当前的头部节点和新节点的next指针是否相等，如果相等，则将头部节点设置为新节点，否则不做任何操作。
        while (!head.compareAndSet(new_node.next, new_node)) {
            new_node.next = head.get();
        }
    }

    public T pop() {
        Node<T> old_head = head.get();//首先获取当前的头部节点
        //使用compareAndSet()方法将头部节点的下一个节点设置为新的头部节点。
        //如果compareAndSet()方法返回false，则说明头部节点已经被其他线程修改过了，需要重新获取头部节点并重试。
        while (old_head != null && !head.compareAndSet(old_head, old_head.next)) {
            old_head = head.get();
        }
        return old_head != null ? old_head.data : null;
    }
}
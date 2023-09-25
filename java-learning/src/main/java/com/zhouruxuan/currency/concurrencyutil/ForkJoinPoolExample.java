package com.zhouruxuan.currency.concurrencyutil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 在这个例子中，我们的任务是计算一个长整数的三倍。如果这个长整数较大（大于16），我们就把这个任务拆分为两个子任务，每个子任务计算一半的长整数的三倍。然后我们并行执行这两个子任务，最后把结果合并起来。
 */
public class ForkJoinPoolExample {

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }
}

class MyRecursiveTask extends RecursiveTask<Long> {

    private long workload = 0;

    public MyRecursiveTask(long workload) {
        this.workload = workload;
    }

    @Override
    protected Long compute() {
        if (this.workload > 16) {
            System.out.println("Splitting workload : " + this.workload);
            List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>(createSubtasks());
            for (RecursiveTask<Long> subtask : subtasks) {
                subtask.fork();
            }
            long result = 0;
            for (MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("Doing workload myself: " + this.workload);
            return workload * 3;
        }
    }

    private List<MyRecursiveTask> createSubtasks() {
        List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workload / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workload / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }
}

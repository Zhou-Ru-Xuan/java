package com.example.springlearning;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @see <a href="https://www.cnblogs.com/huxi2b/p/13668061.html">Kafka多线程消费者案例</a>
 */
public class KafkaConsumerTest {

    @Test
    public void ordinaryConsumerTest() {
        int expectedCount = 50 * 900;
        String brokerId = "localhost:9092";
        String groupId = "test-group";
        String topic = "test";

        OrdinaryConsumer consumer = new OrdinaryConsumer(brokerId, topic, groupId + "-single", expectedCount);
        long start = System.currentTimeMillis();
        consumer.run();
        System.out.println("Single-threaded consumer costs " + (System.currentTimeMillis() - start));

    }

    @Test
    public void multiThreadedConsumerTest() {
        int expectedCount = 50 * 900;
        String brokerId = "localhost:9092";
        String groupId = "test-group";
        String topic = "test";

        MultiThreadedConsumer multiThreadedConsumer = new MultiThreadedConsumer(brokerId, topic, groupId + "-multi", expectedCount);
        long start = System.currentTimeMillis();
        multiThreadedConsumer.run();
        System.out.println("Multi-threaded consumer costs " + (System.currentTimeMillis() - start));

    }
}

/**
 * 单线程Consumer
 */
class OrdinaryConsumer {

    private final Consumer<String, String> consumer;
    private final int expectedCount; // 用于测试的消息数量

    public OrdinaryConsumer(String brokerId, String topic, String groupID, int expectedCount) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerId);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        this.expectedCount = expectedCount;
    }

    public void run() {
        try {
            int alreadyConsumed = 0;
            while (alreadyConsumed < expectedCount) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                alreadyConsumed += records.count();
                records.forEach(this::handleRecord);
            }
        } finally {
            consumer.close();
        }
    }

    private void handleRecord(ConsumerRecord<String, String> record) {
        try {
            // 模拟每条消息10毫秒处理
            Thread.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " finished message processed. Record offset = " + record.offset());
    }
}

/**
 * 消息处理的Runnable类：ConsumerWorker。和上一篇相比，这次最大的不同在于每个Worker只处理相同分区下的消息，而不是向之前那样处理多个分区中的消息。这样做的好处在于一旦某个分区的消息分配给了这个Worker，我可以暂停这个分区的可消费状态，直到这个Worker全部处理完成。如果是混着多个分区的消息一起处理，实现这个就比较困难
 *
 * @param <K>
 * @param <V>
 */
class ConsumerWorker<K, V> {

    private final List<ConsumerRecord<K, V>> recordsOfSamePartition;
    private volatile boolean started = false;
    private volatile boolean stopped = false;
    private final ReentrantLock lock = new ReentrantLock();

    private final long INVALID_COMMITTED_OFFSET = -1L;
    private final AtomicLong latestProcessedOffset = new AtomicLong(INVALID_COMMITTED_OFFSET);
    private final CompletableFuture<Long> future = new CompletableFuture<>();

    public ConsumerWorker(List<ConsumerRecord<K, V>> recordsOfSamePartition) {
        this.recordsOfSamePartition = recordsOfSamePartition;
    }

    public boolean run() {
        lock.lock();
        if (stopped)
            return false;
        started = true;
        lock.unlock();
        for (ConsumerRecord<K, V> record : recordsOfSamePartition) {
            if (stopped)
                break;
            handleRecord(record);
            if (latestProcessedOffset.get() < record.offset() + 1)
                latestProcessedOffset.set(record.offset() + 1);
        }
        return future.complete(latestProcessedOffset.get());
    }

    public long getLatestProcessedOffset() {
        return latestProcessedOffset.get();
    }

    private void handleRecord(ConsumerRecord<K, V> record) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " finished message processed. Record offset = " + record.offset());
    }

    public void close() {
        lock.lock();
        this.stopped = true;
        if (!started) {
            future.complete(latestProcessedOffset.get());
        }
        lock.unlock();
    }

    public boolean isFinished() {
        return future.isDone();
    }

    public long waitForCompletion(long timeout, TimeUnit timeUnit) {
        try {
            return future.get(timeout, timeUnit);
        } catch (Exception e) {
            if (e instanceof InterruptedException)
                Thread.currentThread().interrupt();
            return INVALID_COMMITTED_OFFSET;
        }
    }
}

class MultiThreadedConsumer {

    private final Map<TopicPartition, ConsumerWorker<String, String>> outstandingWorkers = new HashMap<>();
    private final Map<TopicPartition, OffsetAndMetadata> offsetsToCommit = new HashMap<>();
    private long lastCommitTime = System.currentTimeMillis();
    private final Consumer<String, String> consumer;
    private final int DEFAULT_COMMIT_INTERVAL = 3000;
    private final Map<TopicPartition, Long> currentConsumedOffsets = new HashMap<>();
    private final long expectedCount;

    private final static Executor executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() * 10, r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public MultiThreadedConsumer(String brokerId, String topic, String groupID, long expectedCount) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerId);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic), new MultiThreadedRebalanceListener(consumer, outstandingWorkers, offsetsToCommit));
        this.expectedCount = expectedCount;
    }

    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                distributeRecords(records);
                checkOutstandingWorkers();
                commitOffsets();
                if (currentConsumedOffsets.values().stream().mapToLong(Long::longValue).sum() >= expectedCount) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * 对已完成消息处理并提交位移的分区执行resume操作
     */
    private void checkOutstandingWorkers() {
        Set<TopicPartition> completedPartitions = new HashSet<>();
        outstandingWorkers.forEach((tp, worker) -> {
            if (worker.isFinished()) {
                completedPartitions.add(tp);
            }
            long offset = worker.getLatestProcessedOffset();
            currentConsumedOffsets.put(tp, offset);
            if (offset > 0L) {
                offsetsToCommit.put(tp, new OffsetAndMetadata(offset));
            }
        });
        completedPartitions.forEach(outstandingWorkers::remove);
        consumer.resume(completedPartitions);
    }

    /**
     * 提交位移
     */
    private void commitOffsets() {
        try {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastCommitTime > DEFAULT_COMMIT_INTERVAL && !offsetsToCommit.isEmpty()) {
                consumer.commitSync(offsetsToCommit);
                offsetsToCommit.clear();
            }
            lastCommitTime = currentTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将不同分区的消息交由不同的线程，同时暂停该分区消息消费
     *
     * @param records
     */
    private void distributeRecords(ConsumerRecords<String, String> records) {
        if (records.isEmpty())
            return;
        Set<TopicPartition> pausedPartitions = new HashSet<>();
        records.partitions().forEach(tp -> {
            List<ConsumerRecord<String, String>> partitionedRecords = records.records(tp);
            pausedPartitions.add(tp);
            final ConsumerWorker<String, String> worker = new ConsumerWorker<>(partitionedRecords);
            CompletableFuture.supplyAsync(worker::run, executor);
            outstandingWorkers.put(tp, worker);
        });
        consumer.pause(pausedPartitions);
    }

}

/**
 * 多线程Consumer在Rebalance操作开启后要小心处理。首先，主线程的poll方法与Worker线程处理消息是并行执行的。此时如果发生Rebalance，那么有些分区就会被分配给其他Consumer，但Worker线程依然可能正在处理这些分区。因此，就可能出现这样的场景：两个Consumer都会处理这些分区中的消息。这就破坏了消费者组的设计理念。针对这种情况，我们必须要确保要被回收的那些分区的处理必须首先完成，之后才能被重新分配。
 * <p>
 * 总体而言，在要回收分区前，多线程Consumer必须完成：
 * <p>
 * 停止对应的Worker线程
 * 提交位移
 * 当然，一旦分区被重新分配后，事情就变得简单了，我们调用resume恢复这些分区的可消费状态即可。如果这些分区之前就是可以消费的，那么调用resume方法就没有任何效果，总之是一个“无害”操作。MultiThreadedRebalanceListener类完整代码如下：
 */
class MultiThreadedRebalanceListener implements ConsumerRebalanceListener {

    private final Consumer<String, String> consumer;
    private final Map<TopicPartition, ConsumerWorker<String, String>> outstandingWorkers;
    private final Map<TopicPartition, OffsetAndMetadata> offsets;

    public MultiThreadedRebalanceListener(Consumer<String, String> consumer,
                                          Map<TopicPartition, ConsumerWorker<String, String>> outstandingWorkers,
                                          Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.consumer = consumer;
        this.outstandingWorkers = outstandingWorkers;
        this.offsets = offsets;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        Map<TopicPartition, ConsumerWorker<String, String>> stoppedWorkers = new HashMap<>();
        for (TopicPartition tp : partitions) {
            ConsumerWorker<String, String> worker = outstandingWorkers.remove(tp);
            if (worker != null) {
                worker.close();
                stoppedWorkers.put(tp, worker);
            }
        }

        stoppedWorkers.forEach((tp, worker) -> {
            long offset = worker.waitForCompletion(1, TimeUnit.SECONDS);
            if (offset > 0L) {
                offsets.put(tp, new OffsetAndMetadata(offset));
            }
        });

        Map<TopicPartition, OffsetAndMetadata> revokedOffsets = new HashMap<>();
        partitions.forEach(tp -> {
            OffsetAndMetadata offset = offsets.remove(tp);
            if (offset != null) {
                revokedOffsets.put(tp, offset);
            }
        });

        try {
            consumer.commitSync(revokedOffsets);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        consumer.resume(partitions);
    }
}
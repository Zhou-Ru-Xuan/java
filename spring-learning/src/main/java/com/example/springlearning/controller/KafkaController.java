package com.example.springlearning.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@Slf4j
public class KafkaController {
    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    /**
     * AdminClient 创建
     */
    @Autowired
    private KafkaProperties properties;

    @PostMapping("/send")
    public void send(@RequestBody String message) {
        kafkaTemplate.send("my-topic", message);
    }

    /**
     * 获取通知结果
     *
     * @return
     */
    @PostMapping("/sendAndGet")
    public void sendAndGet(@RequestBody String message) {
        // 同步获取结果
        ListenableFuture<SendResult<Object, Object>> future = kafkaTemplate.send("my-topic", message);
        try {
            SendResult<Object, Object> result = future.get();
            LOGGER.info("success send >>> {}", result.getRecordMetadata().topic());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取通知结果
     *
     * @return
     */
    @PostMapping("/sendAndAsyncGet")
    public void sendAndAsyncGet(@RequestBody String message) {
        // 发送消息 - 异步获取通知结果
        kafkaTemplate.send("my-topic", message).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOGGER.error("fail >>>>{}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
                LOGGER.info("async success send >>> {}", objectObjectSendResult.getRecordMetadata().topic());
            }
        });
    }


    @GetMapping("/create/{topicName}/{numPartitions}")
    public String createTopic(@PathVariable String topicName, @PathVariable Integer numPartitions) {
        try (AdminClient client = AdminClient.create(properties.buildAdminProperties())) {
            if (client != null) {
                Collection<NewTopic> newTopics = new ArrayList<>(1);
                newTopics.add(new NewTopic(topicName, numPartitions, (short) 1));
                client.createTopics(newTopics);
            }
        }
        return topicName;
    }
}

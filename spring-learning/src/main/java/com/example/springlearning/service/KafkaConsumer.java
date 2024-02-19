package com.example.springlearning.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void consume(String message) {
        LOGGER.info("Received message : {}", message);
    }
}


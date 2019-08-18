package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendDefault(String key, User user) {
        kafkaTemplate.sendDefault(key, user)
                     .addCallback(result -> log.info("record={}",
                                                     result != null ? result.getProducerRecord() : ""),
                                  e -> log.error(e.getMessage(), e));
    }

    public void send(String topic, String key, User user) {
        kafkaTemplate.send(topic, key, user)
                     .addCallback(result -> log.info("record={}",
                                                     result != null ? result.getProducerRecord() : ""),
                                  e -> log.error(e.getMessage(), e));
    }
}

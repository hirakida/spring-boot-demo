package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageClient {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendDefault(String key, User user) {
        kafkaTemplate.sendDefault(key, user)
                     .thenAccept(result -> log.info("record={}", result.getProducerRecord()));
    }

    public void send(String topic, String key, User user) {
        kafkaTemplate.send(topic, key, user)
                     .thenAccept(result -> log.info("record={}", result.getProducerRecord()));
    }
}

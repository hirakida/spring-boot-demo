package com.example.producer;

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
    private final ListenableFutureCallbackImpl callback;

    public void sendDefault(String key, User user) {
        kafkaTemplate.sendDefault(key, user)
                     .addCallback(callback);
    }

    public void send(String topic, String key, User user) {
        kafkaTemplate.send(topic, key, user)
                     .addCallback(callback);
    }
}

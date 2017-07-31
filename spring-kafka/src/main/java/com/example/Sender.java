package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Sender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String key, String data) {
        kafkaTemplate.send(topic, key, data);
    }

    public void sendDefault(String data) {
        kafkaTemplate.sendDefault(data);
    }
}

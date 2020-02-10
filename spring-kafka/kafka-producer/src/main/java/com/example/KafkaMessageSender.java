package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.example.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageSender {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendDefault(String key, User user) {
        kafkaTemplate.sendDefault(key, user)
                     .addCallback(KafkaMessageSender::successCallback,
                                  KafkaMessageSender::failureCallback);
    }

    public void send(String topic, String key, User user) {
        kafkaTemplate.send(topic, key, user)
                     .addCallback(KafkaMessageSender::successCallback,
                                  KafkaMessageSender::failureCallback);
    }

    private static void successCallback(SendResult<String, User> result) {
        log.info("record={}", result != null ? result.getProducerRecord() : "");
    }

    private static void failureCallback(Throwable e) {
        log.error(e.getMessage(), e);
    }
}

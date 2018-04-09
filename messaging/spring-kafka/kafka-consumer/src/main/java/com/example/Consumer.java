package com.example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.entity.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void defaultTopics(ConsumerRecord<String, User> record) {
        log.info("record={}", record);
    }
}

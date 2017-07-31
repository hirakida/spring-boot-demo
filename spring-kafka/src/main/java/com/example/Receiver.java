package com.example;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

    public final CountDownLatch latch = new CountDownLatch(10);

    @KafkaListener(topics = "topic1")
    public void receive(ConsumerRecord<?, ?> record) {
        log.info("record={}", record);
        latch.countDown();
    }
}

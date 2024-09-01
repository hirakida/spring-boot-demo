package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageSender.class);
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private final KafkaTemplate<String, User> kafkaTemplate;

    public KafkaMessageSender(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessages() {
        LocalDateTime now = LocalDateTime.now();
        long id = COUNTER.incrementAndGet();
        User user = new User(id, "name" + id);

        kafkaTemplate.send("topic1", now.toString(), user)
                     .thenAccept(result -> LOGGER.info("record={}", result.getProducerRecord()));

        kafkaTemplate.sendDefault(now.toString(), user)
                     .thenAccept(result -> LOGGER.info("record={}", result.getProducerRecord()));
    }
}

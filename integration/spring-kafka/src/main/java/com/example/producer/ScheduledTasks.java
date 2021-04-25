package com.example.producer;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private static final AtomicInteger counter = new AtomicInteger();
    private final KafkaMessageClient kafkaMessageClient;

    @Scheduled(cron = "0,15,30,45 * * * * *")
    public void sendMessages() {
        LocalDateTime now = LocalDateTime.now();
        long id = counter.incrementAndGet();
        User user = new User(id, "name" + id);

        kafkaMessageClient.send("topic1", now.toString(), user);
        kafkaMessageClient.sendDefault(LocalDateTime.now().toString(), user);
    }
}

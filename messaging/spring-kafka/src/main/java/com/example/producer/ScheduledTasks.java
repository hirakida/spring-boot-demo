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
    private final KafkaMessageSender kafkaMessageSender;

    @Scheduled(cron = "0,15,30,45 * * * * *")
    public void sendMessages() {
        LocalDateTime now = LocalDateTime.now();
        long id = counter.incrementAndGet();
        User user = new User(id, "name" + id);

        kafkaMessageSender.send("topic1", now.toString(), user);
        kafkaMessageSender.sendDefault(LocalDateTime.now().toString(), user);
    }
}

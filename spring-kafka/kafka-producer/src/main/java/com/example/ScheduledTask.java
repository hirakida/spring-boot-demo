package com.example;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final KafkaMessageSender kafkaMessageSender;

    @Scheduled(cron = "0,30 * * * * *")
    public void task() {
        long id = new Random().nextLong();
        User user = new User(id, "name" + id);
        kafkaMessageSender.sendDefault(LocalDateTime.now().toString(), user);
    }
}

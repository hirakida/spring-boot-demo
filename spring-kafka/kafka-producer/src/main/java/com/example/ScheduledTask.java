package com.example;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final MessageSender messageSender;

    @Scheduled(cron = "0,30 * * * * *")
    public void task() {
        long id = new Random().nextLong();
        User user = new User(id, "name" + id);
        messageSender.sendDefault(LocalDateTime.now().toString(), user);
    }
}

package com.example;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.producer.MessageSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulingTask {
    private final MessageSender messageSender;

    @Scheduled(cron = "0,30 * * * * *")
    public void scheduled() {
        long id = new Random().nextLong();
        User user = new User(id, "name" + id);
        messageSender.sendDefault(LocalDateTime.now().toString(), user)
                     .addCallback(result -> log.info("callback: record={}",
                                                     Objects.requireNonNull(result).getProducerRecord()),
                                  e -> log.error("callback: error", e));
    }
}

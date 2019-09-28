package com.example;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.event.User;
import com.example.event.UserEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedRate = 10000)
    public void publishEvent() {
        int count = COUNTER.getAndIncrement();
        log.info("publish start {}", count);
        User user = new User(count, "name" + count);
        publisher.publishEvent(new UserEvent(user));
        log.info("publish end {}", count);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.example;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.event.UserEvent;
import com.example.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventPublisher {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedRate = 10000)
    public void publishEvent() {
        int count = COUNTER.getAndIncrement();
        User user = new User(count, "name" + count);

        log.info("publishEvent start: {}", count);
        publisher.publishEvent(new UserEvent(user));
        log.info("publishEvent end: {}", count);
    }
}

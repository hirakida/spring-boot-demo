package com.example;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.event.User;
import com.example.event.UserEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTask {
    private final ApplicationEventPublisher eventPublisher;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @Scheduled(fixedRate = 10000)
    public void publishEvent() {
        int count = COUNTER.getAndIncrement();
        log.info("publish start {}", count);
        eventPublisher.publishEvent(new UserEvent(new User(count, "name" + count)));
        log.info("publish end {}", count);
    }
}

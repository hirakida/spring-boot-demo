package com.example;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HelloService {
    private final ApplicationEventPublisher publisher;

    @EventListener
    public void handleEvent(HelloEvent event) {
        log.info("HelloEvent={}", event.getHello());
    }

    public void publishEvent() {
        Hello hello = new Hello("Hello! " + LocalDateTime.now());
        publisher.publishEvent(new HelloEvent(hello));
    }
}

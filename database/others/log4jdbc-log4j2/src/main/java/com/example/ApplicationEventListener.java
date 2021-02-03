package com.example;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        userRepository.findAll()
                      .forEach(u -> log.info("{}", u));
    }
}

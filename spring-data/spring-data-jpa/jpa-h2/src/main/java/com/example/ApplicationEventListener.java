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
    public void readyEvent() {
        log.info("findByName: {}", userRepository.findByName("user1"));
        log.info("findByNameLike: {}", userRepository.findByNameLike("user%"));
        log.info("findByNameStartingWith: {}", userRepository.findByNameStartingWith("user"));
        log.info("findByNameEndingWith: {}", userRepository.findByNameEndingWith("2"));
        log.info("findByNameContaining: {}", userRepository.findByNameContaining("user"));
        log.info("findByIdLessThan: {}", userRepository.findByIdLessThan(4));
        log.info("findByIdGreaterThan: {}", userRepository.findByIdGreaterThan(4));
        log.info("findByEnabledTrue: {}", userRepository.findByEnabledTrue());
        log.info("findByEnabledFalse: {}", userRepository.findByEnabledFalse());
    }
}

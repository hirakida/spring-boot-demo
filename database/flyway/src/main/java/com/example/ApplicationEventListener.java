package com.example;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.repository.AccountRepository;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        accountRepository.findAll()
                         .forEach(account -> log.info("{}", account));
        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }
}

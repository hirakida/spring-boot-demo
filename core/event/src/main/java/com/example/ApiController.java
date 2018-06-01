package com.example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.event.AccountEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/publish/{id}")
    public AccountEvent publish(@PathVariable long id) {
        log.info("publish start {}", id);
        AccountEvent event = new AccountEvent(new Account(id, "name" + id));
        applicationEventPublisher.publishEvent(event);
        log.info("publish end {}", id);
        return event;
    }
}

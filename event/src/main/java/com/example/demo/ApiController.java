package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.Account;
import com.example.demo.core.AccountEvent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
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

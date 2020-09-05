package com.example;

import java.time.LocalDateTime;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.model.Account;
import com.example.model.Person;
import com.example.service.AccountService;
import com.example.service.PersonService;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final UserService userService;
    private final PersonService personService;
    private final AccountService accountService;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        Mono.zip(userService.insert("user6"),
                 personService.insert(new Person(null, "person6", LocalDateTime.now())))
            .block();

        accountService.insert(new Account(null, "account6", LocalDateTime.now()))
                      .then(accountService.findById(1))
                      .flatMap(account -> {
                          account.setName(account.getName() + '_');
                          return accountService.update(account);
                      })
                      .thenMany(accountService.findAll())
                      .subscribe(account -> log.info("{}", account));
    }
}

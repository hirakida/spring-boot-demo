package com.example;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.reactive.TransactionalOperator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TransactionalOperator transactionalOperator;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        transactionalOperator.transactional(Flux.fromIterable(List.of("user6", "user7"))
                                                .map(name -> new User(null, name, LocalDateTime.now()))
                                                .flatMap(userRepository::save)
                                                .doOnNext(user -> log.info("{}", user)))
                             .thenMany(userService.findAll())
                             .subscribe(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

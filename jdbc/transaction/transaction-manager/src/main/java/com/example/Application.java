package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJdbcAuditing
@RequiredArgsConstructor
@Slf4j
public class Application {
    private final UserService userService;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        IntStream.rangeClosed(1, 3)
                 .forEach(i -> {
                     User user = new User();
                     user.setName("name" + i);
                     userService.create(user);
                 });

        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

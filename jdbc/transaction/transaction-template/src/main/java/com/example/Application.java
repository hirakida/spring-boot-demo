package com.example;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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
                     log.info("{}", userService.create(user));
                 });

        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                                        .toList();
        users.forEach(user -> log.info("{}", user));

        User user = users.get(0);
        user.setName("name1-1");
        userService.update(user);

        userService.delete(users.get(1).getId());

        userRepository.findAll()
                      .forEach(entity -> log.info("{}", entity));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

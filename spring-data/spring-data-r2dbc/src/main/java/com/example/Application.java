package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application {
    private final UserService2 userService;
    private final UserRepository2 userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        userService.insert("user6", "user7")
                   .thenMany(userRepository.findAll())
                   .subscribe(user -> log.info("{}", user));

        userService.insert("user8")
                   .then(userRepository.findById(8))
                   .map(user -> {
                       user.setName(user.getName() + "__");
                       return user;
                   })
                   .flatMap(userRepository::update)
                   .thenMany(userRepository.findAll())
                   .subscribe(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

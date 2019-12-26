package com.example;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        userRepository.deleteAll();
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String name = "user" + i;
                     User user = new User();
                     user.setName(name);
                     user.setMessage("Hello " + name + '!');
                     user.setCreatedAt(LocalDateTime.now());
                     userRepository.save(user);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

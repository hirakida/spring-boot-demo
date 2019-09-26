package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
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

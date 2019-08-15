package com.example;

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
    public void run(String... strings) throws Exception {
        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     User user = new User();
                     user.setName("user" + i);
                     userRepository.save(user);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

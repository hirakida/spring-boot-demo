package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        List<User> users = userRepository.findAll().stream()
                                         .peek(user -> user.setName(user.getName() + '!'))
                                         .collect(Collectors.toList());
        userRepository.batchUpdate(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

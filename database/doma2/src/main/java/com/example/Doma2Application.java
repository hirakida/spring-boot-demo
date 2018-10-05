package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.User;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Doma2Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
        // use SelectType.STREAM
        List<User> users = userRepository.findAll(stream -> stream.collect(Collectors.toList()));
        users.forEach(user -> log.info("{}", user));

        // use SelectType.COLLECT
        users = userRepository.findAll(Collectors.toList());
        users.forEach(user -> log.info("{}", user));

        Map<Long, List<User>> accountMap = userRepository.findAll(Collectors.groupingBy(User::getId));
        accountMap.forEach((key, value) -> log.info("key={} value={}", key, value));
    }

    public static void main(String[] args) {
        SpringApplication.run(Doma2Application.class, args);
    }
}

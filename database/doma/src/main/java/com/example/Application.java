package com.example;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        // SelectType.STREAM
        List<User> users = userRepository.findAll(stream -> stream.collect(toList()));
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        users = userRepository.findAll(toList());
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        Map<Long, List<User>> userMap = userRepository.findAll(groupingBy(User::getId));
        userMap.forEach((key, value) -> log.info("key={} value={}", key, value));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

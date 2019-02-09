package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class DomaApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
        // SelectType.STREAM
        List<User> users = userRepository.findAll(stream -> stream.collect(Collectors.toList()));
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        users = userRepository.findAll(Collectors.toList());
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        Map<Long, List<User>> userMap = userRepository.findAll(Collectors.groupingBy(User::getId));
        userMap.forEach((key, value) -> log.info("key={} value={}", key, value));
    }

    public static void main(String[] args) {
        SpringApplication.run(DomaApplication.class, args);
    }
}

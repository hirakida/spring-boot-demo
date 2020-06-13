package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        List<User> users = userRepository.findAll();
        log.info("{}", users);

        userRepository.findById(users.get(0).getId())
                      .ifPresent(user -> log.info("{}", user));
    }
}

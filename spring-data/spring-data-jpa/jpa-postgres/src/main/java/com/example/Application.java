package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) {
        List<Person> users = personRepository.findAll();
        log.info("{}", users);

        personRepository.findById(users.get(0).getId())
                        .ifPresent(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

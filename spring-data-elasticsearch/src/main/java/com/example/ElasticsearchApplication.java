package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ElasticsearchApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String name = "user" + i;
                     userRepository.deleteByName(name);
                     userRepository.save(User.of(name, LocalDateTime.now()));
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}

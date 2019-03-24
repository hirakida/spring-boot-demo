package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.service.AccountService;
import com.example.web.Role;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class LoginApplication implements CommandLineRunner {
    private final AccountService accountService;

    @Override
    public void run(String... args) {
        IntStream.range(1, 6)
                 .forEach(i -> {
                     Role role;
                     if (i < 2) {
                         role = Role.ADMIN;
                     } else if (i < 4) {
                         role = Role.USER;
                     } else {
                         role = Role.NO_AUTHORITIES;
                     }
                     String name = "user" + i;
                     accountService.create(name, name + "@example.com", "pass" + i, role);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}

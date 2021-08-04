package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserMapper userMapper;

    @Override
    public void run(String... args) {
        UserForm form1 = new UserForm();
        form1.setId(1);
        form1.setName("name1");
        form1.setEmail("name1@test.com");
        UserForm form2 = new UserForm();
        form2.setId(2);
        form2.setName("name2");
        form2.setEmail("name2@test.com");

        log.info("form1: {}", userMapper.toUser(form1));
        log.info("form2: {}", userMapper.toUser(form2));
        log.info("list: {}", userMapper.toUsers(List.of(form1, form2)));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

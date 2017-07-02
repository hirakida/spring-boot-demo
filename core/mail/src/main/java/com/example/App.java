package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class App implements CommandLineRunner {

    final MailHelper mailSender;

    @Override
    public void run(String... var1) throws Exception {
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

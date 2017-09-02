package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringAmqpApplication implements CommandLineRunner {

    final Sender sender;

    @Override
    public void run(String... args) throws Exception {
        sender.send("Hello");
        sender.send("Spring AMQP");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAmqpApplication.class, args);
    }
}

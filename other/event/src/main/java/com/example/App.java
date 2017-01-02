package com.example;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void run(String... strings) throws IOException {
        log.info("run start");
        AccountEvent event = new AccountEvent(new Account(1L, "name1"));
        applicationEventPublisher.publishEvent(event);
        log.info("run end");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

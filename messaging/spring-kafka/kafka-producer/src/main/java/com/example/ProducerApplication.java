package com.example;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ProducerApplication {

    private final Producer producer;

    @Scheduled(cron = "0,30 * * * * *")
    public void scheduled() {
        long id = new Random().nextLong();
        User user = new User(id, "name" + id);
        producer.sendDefault(LocalDateTime.now().toString(), user)
                .addCallback(result -> log.info("callback: record={}", result.getProducerRecord()),
                             e -> log.error("callback: error", e));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}

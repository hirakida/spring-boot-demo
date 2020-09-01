package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class Application {
    private final SuccessCount successCount;

    public Application(SuccessCount successCount) {
        successCount.setName("Success Count");
        this.successCount = successCount;
    }

    @Scheduled(fixedRate = 5000)
    public void run() {
        successCount.increment();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

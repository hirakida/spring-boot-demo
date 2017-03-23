package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class App implements CommandLineRunner {

    final ApiClient apiClient;

    @Override
    public void run(String... var1) throws Exception {

        apiClient.retryable(200);
        apiClient.retryable(400);
        apiClient.retryable(500);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

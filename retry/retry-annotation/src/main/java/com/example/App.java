package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableRetry
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class App implements CommandLineRunner {

    final ApiClient apiClient;

    @Override
    public void run(String... var1) throws Exception {

        // 正常終了 retryなし
        String result = apiClient.retryable(200);
        log.info(result);
        // 400 error retryなし
        result = apiClient.retryable(400);
        log.info(result);
        // 500 error retryあり
        result = apiClient.retryable(500);
        log.info(result);

        apiClient.retryable2(200);
        apiClient.retryable2(400);
        apiClient.retryable2(500);
        // 504のみretry
        apiClient.retryable2(504);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

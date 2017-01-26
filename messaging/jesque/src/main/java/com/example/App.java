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

    private JesqueClient jesqueClient;

    @Override
    public void run(String... args) throws Exception {
        jesqueClient.enqueue("test1-1", "Job1");
        jesqueClient.enqueue("test2-1", "Job2");
        jesqueClient.enqueue("test3-1", "Job3");

        // 全く同じjobを2回送信してもイベントは1つしか来ないみたい
        jesqueClient.enqueue("test1-2", "Job1");
        jesqueClient.enqueue("test2-2", "Job2");
        jesqueClient.enqueue("test3-2", "Job3");

        jesqueClient.enqueue("test1-3", "Job1");
        jesqueClient.enqueue("test2-3", "Job2");
        jesqueClient.enqueue("test3-3", "Job3");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class App implements CommandLineRunner {

    private final MessageSender messageSender;

    @Override
    public void run(String... args) throws Exception {

        IntStream.range(0, 5)
                 .forEach(i -> {
                     String text = "text" + i;
                     messageSender.sendText(text);
                     messageSender.sendMessage(text);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

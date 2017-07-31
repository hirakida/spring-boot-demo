package com.example;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class SpringKafkaApplication implements CommandLineRunner {

    private final Sender sender;
    private final Receiver receiver;

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        IntStream.rangeClosed(1, 10)
                 .forEach(i -> sender.sendDefault("foo" + i));
        receiver.latch.await(60, TimeUnit.SECONDS);
    }
}

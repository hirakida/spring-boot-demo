package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.custom.event.User;
import com.example.custom.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ApplicationEventPublisher publisher;

    @Override
    public void run(String... args) {
        log.info("CommandLineRunner start");
        IntStream.rangeClosed(1, 3)
                 .forEach(i -> {
                     User user = new User(i, "name" + i);
                     publisher.publishEvent(new UserCreatedEvent(user));
                 });
        log.info("CommandLineRunner end");
    }
}

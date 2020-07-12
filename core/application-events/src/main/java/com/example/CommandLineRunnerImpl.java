package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.event.User;
import com.example.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ApplicationEventPublisher publisher;

    @Override
    public void run(String... args) {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User(i, "name" + i);
                     publisher.publishEvent(new UserCreatedEvent(user));
                 });
    }
}

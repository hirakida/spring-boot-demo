package com.example;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     userRepository.save(user);
                 });
    }
}

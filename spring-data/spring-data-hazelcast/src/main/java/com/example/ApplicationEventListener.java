package com.example;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        userRepository.deleteAll();

        LongStream.rangeClosed(1, 5)
                  .forEach(i -> {
                      User user = new User();
                      user.setUserId(i);
                      user.setName("user" + i);
                      user.setCreatedAt(LocalDateTime.now());
                      userRepository.save(user);
                  });
    }
}

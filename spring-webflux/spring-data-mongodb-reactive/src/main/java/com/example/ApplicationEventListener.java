package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        final User[] users = IntStream.rangeClosed(1, 5)
                                      .mapToObj(i -> {
                                          User user = new User();
                                          user.setName("name" + i);
                                          user.setAge(i * 10);
                                          return user;
                                      })
                                      .toArray(User[]::new);
        userRepository.saveAll(Flux.just(users))
                      .then()
                      .subscribe();
    }
}

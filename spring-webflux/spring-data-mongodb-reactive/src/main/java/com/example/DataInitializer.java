package com.example;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Profile("!test")
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        final List<User> users = IntStream.rangeClosed(1, 5)
                                          .mapToObj(i -> {
                                              User user = new User();
                                              user.setName("name" + i);
                                              user.setAge(i * 10);
                                              return user;
                                          }).collect(toList());
        Flux.fromIterable(users)
            .flatMap(userRepository::save)
            .subscribe(user -> log.info("{}", user));
    }
}

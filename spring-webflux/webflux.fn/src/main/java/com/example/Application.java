package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        final User[] users = IntStream.rangeClosed(1, 5)
                                      .mapToObj(i -> {
                                          User user = new User();
                                          user.setName("name" + i);
                                          user.setAge(20 + i);
                                          return user;
                                      })
                                      .toArray(User[]::new);
        userRepository.saveAll(Flux.just(users))
                      .then()
                      .subscribe();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

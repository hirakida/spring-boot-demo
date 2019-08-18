package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        final User[] users = IntStream.rangeClosed(1, 5)
                                      .mapToObj(i -> {
                                          User user = new User();
                                          user.setName("name" + i);
                                          user.setAge(20 * i);
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

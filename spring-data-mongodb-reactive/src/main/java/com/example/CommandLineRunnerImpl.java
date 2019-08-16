package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
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

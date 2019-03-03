package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
@SpringBootApplication
@RequiredArgsConstructor
public class RouterFunctionApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        final User[] users = IntStream.rangeClosed(1, 5)
                                      .mapToObj(i -> User.of("name" + i, 20 + i))
                                      .toArray(User[]::new);
        userRepository.saveAll(Flux.just(users))
                      .then()
                      .subscribe();
    }

    public static void main(String[] args) {
        SpringApplication.run(RouterFunctionApplication.class, args);
    }
}

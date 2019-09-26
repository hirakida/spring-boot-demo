package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        List<User> users = IntStream.rangeClosed(1, 6)
                                    .mapToObj(i -> {
                                        User user = new User();
                                        user.setName("user" + i);
                                        return user;
                                    })
                                    .collect(Collectors.toList());
        userRepository.saveAll(users);
    }
}

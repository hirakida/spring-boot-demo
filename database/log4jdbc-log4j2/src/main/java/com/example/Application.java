package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        List<User> users = userRepository.findAll();

        User user = new User();
        user.setName("user4");
        userRepository.save(user);

        userRepository.findById(users.get(0).getId())
                      .ifPresent(u -> {
                          u.setName("user1-2");
                          userRepository.save(u);
                      });

        userRepository.deleteById(users.get(1).getId());

        users = userRepository.findAll();
        log.info("{}", users);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

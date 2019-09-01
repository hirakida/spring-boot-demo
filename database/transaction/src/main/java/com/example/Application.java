package com.example;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.service.UserService1;
import com.example.service.UserService2;
import com.example.service.UserService3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserService1 userService1;
    private final UserService2 userService2;
    private final UserService3 userService3;
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### start #####");
        User user = userService1.create("name1");
        log.info("{}", user);

        user = userService2.create("name2");
        log.info("{}", user);

        user = userService3.create("name3");
        log.info("{}", user);

        user = userService3.update(user.getId(), "name3_2");
        log.info("{}", user);

        userService3.delete(user.getId());

        List<User> users = userRepository.findAll();
        log.info("{}", users);
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

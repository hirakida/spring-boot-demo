package com.example;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        List<User> users = userRepository.findAll();

        User user = new User();
        user.setName("user4");
        userRepository.save(user);

        user.setName("user4_");
        userRepository.save(user);

        userRepository.deleteById(users.get(0).getId());

        userRepository.findAll()
                      .forEach(u -> log.info("{}", u));
    }
}

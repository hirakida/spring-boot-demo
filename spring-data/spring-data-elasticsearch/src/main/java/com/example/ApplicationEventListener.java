package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        userRepository.deleteAll();
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> {
                                        String name = "user" + i;
                                        User user = new User();
                                        user.setName(name);
                                        user.setMessage("Hello " + name + '!');
                                        return user;
                                    })
                                    .collect(Collectors.toList());
        userRepository.saveAll(users);
    }
}

package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final UserRepository userRepository;
    private final UserClient client;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        userRepository.deleteAll();
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String name = "user" + i;
                     User user = new User();
                     user.setName(name);
                     user.setMessage("Hello " + name + '!');
                     user.setCreatedAt(LocalDateTime.now());
                     userRepository.save(user);
                 });

        List<SearchHit<User>> response = client.search("user1");
        log.info("{}", response);
    }
}

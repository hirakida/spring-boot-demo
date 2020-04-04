package com.example;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        // SelectType.STREAM
        List<User> users = userRepository.findAll(stream -> stream.collect(toList()));
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        users = userRepository.findAll(toList());
        users.forEach(user -> log.info("{}", user));

        // SelectType.COLLECT
        Map<Long, List<User>> userMap = userRepository.findAll(groupingBy(User::getId));
        userMap.forEach((key, value) -> log.info("key={} value={}", key, value));
    }
}

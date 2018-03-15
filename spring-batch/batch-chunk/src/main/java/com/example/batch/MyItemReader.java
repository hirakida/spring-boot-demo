package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyItemReader implements ItemReader<User> {

    private final UserRepository userRepository;

    @Override
    public User read() throws Exception {
        List<User> users = userRepository.findByEnabledTrue();
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        log.info("read {}", user);
        return user;
    }
}

package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.core.User;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemReaderImpl implements ItemReader<User> {
    private final UserRepository userRepository;

    @Nullable
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

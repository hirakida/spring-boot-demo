package com.example.batch;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.User;
import com.example.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemReaderImpl implements ItemStreamReader<User> {
    private final UserRepository userRepository;
    private Iterator<User> users;

    @Override
    public void open(ExecutionContext executionContext) {
        List<User> users = userRepository.findByEnabledFalse();
        log.info("open: {}", users);
        this.users = users.iterator();
    }

    @Nullable
    @Override
    public User read() throws Exception {
        if (!users.hasNext()) {
            log.info("read: null");
            return null;
        }

        User user = users.next();
        log.info("read: {}", user);
        return user;
    }

    @Override
    public void update(ExecutionContext executionContext) {
        log.info("update");
    }

    @Override
    public void close() {
        log.info("close");
    }
}

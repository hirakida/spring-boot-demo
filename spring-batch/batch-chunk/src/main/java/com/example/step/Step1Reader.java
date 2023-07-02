package com.example.step;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
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
public class Step1Reader implements ItemStreamReader<User> {
    private final UserRepository userRepository;
    private Iterator<User> users;

    @BeforeRead
    public void beforeRead() {
        log.info("BeforeRead");
    }

    @AfterRead
    public void afterRead(User user) {
        log.info("AfterRead {}", user);
    }

    @OnReadError
    public void onReadError(Exception e) {
        log.error("OnReadError", e);
    }

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
        log.info("update: {}", executionContext);
    }

    @Override
    public void close() {
        log.info("close");
    }
}

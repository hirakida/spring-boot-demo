package com.example.step;

import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.User;
import com.example.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Step1Writer implements ItemWriter<User> {
    private final UserRepository userRepository;

    @BeforeWrite
    public void beforeWrite(Chunk<User> users) {
        log.info("BeforeWrite {}", users);
    }

    @AfterWrite
    public void afterWrite(Chunk<User> users) {
        log.info("AfterWrite {}", users);
    }

    @OnWriteError
    public void onWriteError(Exception e, Chunk<User> users) {
        log.error("OnWriteError {}", users, e);
    }

    @Override
    public void write(Chunk<? extends User> users) throws Exception {
        log.info("write: {}", users);
        userRepository.saveAll(users);
    }
}

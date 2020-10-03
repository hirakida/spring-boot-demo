package com.example.batch;

import java.util.List;

import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
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
    public void beforeWrite(List<User> users) {
        log.info("BeforeWrite {}", users);
    }

    @AfterWrite
    public void afterWrite(List<User> users) {
        log.info("AfterWrite {}", users);
    }

    @Override
    public void write(List<? extends User> users) throws Exception {
        log.info("write: {}", users);
        userRepository.saveAll(users);
    }
}

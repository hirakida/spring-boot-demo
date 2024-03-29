package com.example.step;

import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Step1Processor implements ItemProcessor<User, User> {
    @BeforeProcess
    public void beforeProcess(User user) {
        log.info("BeforeProcess {}", user);
    }

    @AfterProcess
    public void afterProcess(User before, User after) {
        log.info("AfterProcess {} {}", before, after);
    }

    @OnProcessError
    public void onProcessError(User user, Exception e) {
        log.error("OnProcessError {}", user, e);
    }

    @Override
    public User process(User user) throws Exception {
        log.info("process: {}", user);
        user.setEnabled(true);
        return user;
    }
}

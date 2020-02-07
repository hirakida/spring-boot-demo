package com.example.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ItemProcessorImpl implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        log.info("process: {}", user);
        user.setEnabled(true);
        return user;
    }
}

package com.example.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.User;
import com.example.UserRepository;
import com.example.event.UserEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService1 {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public User create(String name) {
        log.info("##### @Transactional start #####");

        User user = new User();
        user.setName(name);
        userRepository.saveAndFlush(user);
        eventPublisher.publishEvent(new UserEvent(user));

        log.info("##### @Transactional end ######");
        return user;
    }
}

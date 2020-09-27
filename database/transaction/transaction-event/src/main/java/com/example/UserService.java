package com.example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public User create(User user) {
        log.info("##### start #####");

        userRepository.save(user);
        eventPublisher.publishEvent(new UserEvent(user));

        log.info("##### end ######");
        return user;
    }
}

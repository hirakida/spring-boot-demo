package com.example;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        User user = new User();
        user.setName("user6");
        userRepository.save(user);

        userRepository.findByName("user1")
                      .forEach(entity -> {
                          entity.setName(entity.getName() + '_');
                          userRepository.save(entity);
                      });
    }
}

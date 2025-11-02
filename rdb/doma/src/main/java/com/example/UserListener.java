package com.example;

import java.time.LocalDateTime;

import org.seasar.doma.boot.event.annotation.HandlePreInsert;
import org.seasar.doma.boot.event.annotation.HandlePreUpdate;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    @HandlePreInsert
    public void preInsert(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    @HandlePreUpdate
    public void preUpdate(User user) {
        user.setUpdatedAt(LocalDateTime.now());
    }
}

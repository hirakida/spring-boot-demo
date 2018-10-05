package com.example.core;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.stereotype.Component;

@Component
public class UserListener implements EntityListener<User> {

    @Override
    public void preInsert(User entity, PreInsertContext<User> context) {
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void preUpdate(User entity, PreUpdateContext<User> context) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}

package com.example.core;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.stereotype.Component;

@Component
public class AccountListener implements EntityListener<Account> {

    @Override
    public void preInsert(Account entity, PreInsertContext<Account> context) {
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void preUpdate(Account entity, PreUpdateContext<Account> context) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}

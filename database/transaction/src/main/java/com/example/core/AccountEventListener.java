package com.example.core;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountEventListener {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(AccountEvent event) {
        log.info("beforeCommit: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(AccountEvent event) {
        log.info("afterCommit: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(AccountEvent event) {
        log.info("afterRollback: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(AccountEvent event) {
        log.info("afterCompletion: {}", event);
    }

    @SuppressWarnings("serial")
    public static class AccountEvent extends ApplicationEvent {
        public AccountEvent(Account account) {
            super(account);
        }
    }
}

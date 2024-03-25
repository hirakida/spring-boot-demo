package com.example.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEventListener {
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(UserEvent event) {
        log.info("beforeCommit: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(UserEvent event) {
        log.info("afterCommit: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(UserEvent event) {
        log.info("afterRollback: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(UserEvent event) {
        log.info("afterCompletion: {}", event);
    }
}

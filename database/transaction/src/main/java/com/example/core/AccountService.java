package com.example.core;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.core.AccountEventListener.AccountEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final PlatformTransactionManager transactionManager;
    private final DefaultTransactionDefinition defaultTransactionDefinition;
    private final TransactionTemplate transactionTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Declarative Transaction
     */
    @Transactional
    public Account create(String name) {
        log.info("### create start ###");
        Account account = new Account();
        account.setName(name);
        accountRepository.saveAndFlush(account);
        log.info("### create end ###");

        AccountEvent event = new AccountEvent(account);
        applicationEventPublisher.publishEvent(event);
        return account;
    }

    /**
     * Programmatic Transaction
     */
    public void createTxManager(String name) {
        log.info("### createTxManager start ###");
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        Account account = new Account();
        account.setName(name);
        try {
            accountRepository.save(account);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
        log.info("### createTxManager end ###");
    }

    /**
     * Programmatic Transaction
     * TransactionTemplate
     */
    public void createTransactionTemplate(String name) {
        log.info("### createTransactionTemplate start ###");
        transactionTemplate.execute(status -> {
            Account account = new Account();
            account.setName(name);
            accountRepository.save(account);
            return account;
        });
        log.info("### createTransactionTemplate end ###");
    }

    /**
     * Programmatic Transaction
     * TransactionTemplate
     */
    public void deleteTransactionTemplate(String name) {
        log.info("### deleteTransactionTemplate start ###");

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountRepository.deleteByName(name);
            }
        });

        log.info("### deleteTransactionTemplate end ###");
    }
}

package com.example.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.entity.Account;
import com.example.event.AccountEvent;
import com.example.repository.AccountRepository;

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

    @Transactional
    public Account create(String name) {
        log.info("##### @Transactional start #####");
        Account account = new Account();
        account.setName(name);
        accountRepository.saveAndFlush(account);
        publishEvent(account);
        log.info("##### @Transactional end ######");
        return account;
    }

    /**
     * Programmatic Transaction Management
     * PlatformTransactionManager
     */
    public Account create2(String name) {
        log.info("##### PlatformTransactionManager start #####");

        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        Account account = new Account();
        account.setName(name);
        accountRepository.save(account);
        publishEvent(account);
        try {
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }

        log.info("##### PlatformTransactionManager end #####");
        return account;
    }

    /**
     * Programmatic Transaction Management
     * TransactionTemplate
     */
    public Account update(int id, String name) {
        log.info("##### TransactionTemplate start #####");
        Account account = transactionTemplate.execute(status -> {
            return accountRepository.findById(id)
                                    .map(entity -> {
                                        entity.setName(name);
                                        return accountRepository.save(entity);
                                    })
                                    .orElseThrow();
        });
        log.info("##### TransactionTemplate end #####");
        return account;
    }

    public void delete(int id) {
        log.info("##### TransactionTemplate start #####");
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountRepository.deleteById(id);
            }
        });
        log.info("##### TransactionTemplate end #####");
    }

    private void publishEvent(Account account) {
        AccountEvent event = new AccountEvent(account);
        applicationEventPublisher.publishEvent(event);
    }
}

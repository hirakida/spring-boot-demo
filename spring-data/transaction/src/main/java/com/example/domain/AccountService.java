package com.example.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.domain.AccountEventListener.AccountEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    final AccountRepository accountRepository;

    final PlatformTransactionManager transactionManager;

    final DefaultTransactionDefinition defaultTransactionDefinition;

    final TransactionTemplate transactionTemplate;

    final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 宣言的トランザクション
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
     * 明示的トランザクション
     */
    public void createUseTxManager(String name) {
        log.info("### createUseTxManager start ###");

        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            Account account = new Account();
            account.setName(name);
            accountRepository.save(account);
        } catch (RuntimeException e) {
            transactionManager.rollback(status);
            throw e;
        }
        transactionManager.commit(status);

        log.info("### createUseTxManager end ###");
    }

    /**
     * 明示的トランザクション
     * TransactionTemplateを使用する
     * commitやrollbackの呼出しを隠蔽してくれる
     */
    public void createUseTransactionTemplate(String name) {
        log.info("### createUseTransactionTemplate start ###");

        transactionTemplate.execute(new TransactionCallback<Account>() {
            @Override
            public Account doInTransaction(TransactionStatus transactionStatus) {
                Account account = new Account();
                account.setName(name);
                accountRepository.save(account);
                return account;
            }
        });

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountRepository.deleteByName(name);
            }
        });

        log.info("### createUseTransactionTemplate end ###");
    }
}

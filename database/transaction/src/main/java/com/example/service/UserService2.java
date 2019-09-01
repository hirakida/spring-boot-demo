package com.example.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.User;
import com.example.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Programmatic Transaction Management
 * PlatformTransactionManager
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService2 {
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final DefaultTransactionDefinition defaultTransactionDefinition;

    public User create(String name) {
        log.info("##### PlatformTransactionManager start #####");

        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        User user = new User();
        user.setName(name);
        userRepository.save(user);

        try {
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }

        log.info("##### PlatformTransactionManager end #####");
        return user;
    }
}

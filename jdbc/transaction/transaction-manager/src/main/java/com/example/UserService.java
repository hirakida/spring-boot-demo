package com.example;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final DefaultTransactionDefinition defaultTransactionDefinition;

    public User create(User user) {
        log.info("##### start #####");
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        userRepository.save(user);

        try {
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }

        log.info("##### end #####");
        return user;
    }
}

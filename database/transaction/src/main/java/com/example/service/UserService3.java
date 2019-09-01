package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.User;
import com.example.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Programmatic Transaction Management
 * TransactionTemplate
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService3 {
    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;

    public User create(String name) {
        log.info("##### TransactionTemplate start #####");

        User created = transactionTemplate.execute(status -> {
            User user = new User();
            user.setName(name);
            return userRepository.save(user);
        });

        log.info("##### TransactionTemplate end #####");
        return created;
    }

    public User update(int id, String name) {
        log.info("##### TransactionTemplate start #####");

        User user = transactionTemplate.execute(status -> {
            return userRepository.findById(id)
                                 .map(entity -> {
                                     entity.setName(name);
                                     return userRepository.save(entity);
                                 })
                                 .orElseThrow();
        });
        log.info("##### TransactionTemplate end #####");

        return user;
    }

    public void delete(int id) {
        log.info("##### TransactionTemplate start #####");

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                userRepository.deleteById(id);
            }
        });

        log.info("##### TransactionTemplate end #####");
    }
}

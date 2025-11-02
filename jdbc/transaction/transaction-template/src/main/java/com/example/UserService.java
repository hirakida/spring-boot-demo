package com.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;

    public User create(User user) {
        log.info("##### start #####");
        User created = transactionTemplate.execute(status -> userRepository.save(user));
        log.info("#####  end #####");
        return created;
    }

    public void update(User user) {
        log.info("##### start #####");

        transactionTemplate.execute(status -> {
            return userRepository.findById(user.getId())
                                 .map(entity -> {
                                     entity.setName(user.getName());
                                     return userRepository.save(entity);
                                 })
                                 .orElseThrow();
        });

        log.info("##### end #####");
    }

    public void delete(int id) {
        log.info("##### start #####");

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                userRepository.deleteById(id);
            }
        });

        log.info("##### end #####");
    }
}

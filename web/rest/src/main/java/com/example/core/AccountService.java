package com.example.core;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.exception.ForbiddenException;

@Service
public class AccountService {

    public Account findById(long id) {
        if (id > 10L) {
            throw new ForbiddenException("access denied");
        }
        Account account = new Account();
        account.setId(id);
        account.setName("name" + id);
        account.setCreatedAt(LocalDateTime.now());
        if (id % 2 == 0) {
            account.setGender(Gender.MALE);
            account.setCard(Optional.of("card" + id));
        } else {
            account.setGender(Gender.FEMALE);
            account.setCard(Optional.empty());
        }
        return account;
    }

    public Account create(Account account) {
        account.setId(new Random().nextInt());
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }
}

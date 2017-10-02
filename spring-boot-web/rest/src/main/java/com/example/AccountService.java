package com.example;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account findById(long id) {
        if (id > 10L) {
            throw new ForbiddenException("access denied");
        } else if (id > 5) {
            return create(id, Gender.MALE);
        } else {
            return create(id, Gender.FEMALE, "card" + id);
        }
    }

    public Account create(long id, Gender gender) {
        return new Account(id, "name" + id, gender, Optional.empty(), LocalDateTime.now());
    }

    public Account create(long id, Gender gender, String card) {
        return new Account(id, "name" + id, gender, Optional.of(card), LocalDateTime.now());
    }
}

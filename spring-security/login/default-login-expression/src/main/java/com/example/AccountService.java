package com.example;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    public Account findAccount(String username) {
        log.info("username={}", username);
        return new Account(1L, username, "password", AuthorityUtils.NO_AUTHORITIES);
    }

    public Account findAccount(UserDetails userDetails) {
        log.info("userDetails={}", userDetails);
        return new Account(1L,
                           userDetails.getUsername(),
                           userDetails.getPassword(),
                           userDetails.getAuthorities());
    }
}

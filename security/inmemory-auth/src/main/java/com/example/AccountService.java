package com.example;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    public Account findAccount(String username) {
        return new Account(1L, username, "-", AuthorityUtils.NO_AUTHORITIES);
    }

    public Account findAccount(UserDetails userDetails) {
        return new Account(1L,
                           userDetails.getUsername(),
                           userDetails.getPassword(),
                           userDetails.getAuthorities());
    }
}

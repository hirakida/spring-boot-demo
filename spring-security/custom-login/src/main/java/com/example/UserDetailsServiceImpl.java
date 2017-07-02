package com.example;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final List<Account> accounts;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        accounts = ImmutableList.of(new Account(1L, "user1", passwordEncoder.encode("pass1")),
                                    new Account(2L, "user2", passwordEncoder.encode("pass2")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .map(UserDetailsImpl::new)
                       .findFirst()
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final List<Account> accounts;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        accounts = Arrays.asList(account(1L), account(2L));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return accounts.stream()
                       .filter(account -> account.getName().equals(username))
                       .map(UserDetailsImpl::new)
                       .findFirst()
                       .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private  Account account(long id) {
        Account account = new Account();
        account.setId(id);
        account.setName("user" + id);
        account.setPassword(passwordEncoder.encode("password" + id));
        account.setEmail(account.getName() + "@example.com");
        return account;
    }
}

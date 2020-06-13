package com.example;

import static com.example.model.Role.ROLE_ADMIN;
import static com.example.model.Role.ROLE_USER;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.model.Account;
import com.example.userdetails.AccountService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        List<Account> accounts =
                List.of(newAccount(1, AuthorityUtils.createAuthorityList(ROLE_ADMIN.name())),
                        newAccount(2, AuthorityUtils.createAuthorityList(ROLE_USER.name())),
                        newAccount(3, AuthorityUtils.createAuthorityList(ROLE_ADMIN.name(), ROLE_USER.name())),
                        newAccount(4, AuthorityUtils.NO_AUTHORITIES));
        accountService.addAll(accounts);
    }

    private Account newAccount(long id, List<GrantedAuthority> roles) {
        String password = passwordEncoder.encode("pass" + id);
        String email = "user" + id + "@example.com";
        return new Account(id, "user" + id, password, email, roles);
    }
}

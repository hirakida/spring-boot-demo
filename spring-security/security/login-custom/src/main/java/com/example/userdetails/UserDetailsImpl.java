package com.example.userdetails;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.model.Account;
import com.example.model.Role;

@SuppressWarnings("serial")
public class UserDetailsImpl extends User {
    private final Account account;

    public UserDetailsImpl(Account account) {
        super(account.getName(), account.getPassword(), toAuthorities(account.getRoles()));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    private static List<GrantedAuthority> toAuthorities(List<Role> roles) {
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toUnmodifiableList());
    }
}

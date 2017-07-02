package com.example;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class UserDetailsImpl extends User {
    private final Account account;

    public UserDetailsImpl(Account account) {
        super(account.getName(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.account = account;
    }
}

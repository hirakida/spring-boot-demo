package com.example;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("serial")
public class Account implements Serializable {
    private Long id;
    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> authorityList;
}

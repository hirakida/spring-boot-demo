package com.example.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Account implements Serializable {
    private long id;
    private String name;
    private String password;
    private String email;
    private List<GrantedAuthority> roles;
}

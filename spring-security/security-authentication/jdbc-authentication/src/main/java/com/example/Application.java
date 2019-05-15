package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.entity.Authority;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        SimpleJdbcInsert userJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("users");
        SimpleJdbcInsert authJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("authorities");

        // User
        User user = new User("user1", passwordEncoder.encode("pass1"), true);
        userJdbcInsert.execute(new BeanPropertySqlParameterSource(user));
        Authority authority = new Authority("user1", "ROLE_USER");
        authJdbcInsert.execute(new BeanPropertySqlParameterSource(authority));

        // User, Admin
        user = new User("user2", passwordEncoder.encode("pass2"), true);
        userJdbcInsert.execute(new BeanPropertySqlParameterSource(user));
        authority = new Authority("user2", "ROLE_USER");
        authJdbcInsert.execute(new BeanPropertySqlParameterSource(authority));
        authority = new Authority("user2", "ROLE_ADMIN");
        authJdbcInsert.execute(new BeanPropertySqlParameterSource(authority));

        // disabled
        user = new User("user3", passwordEncoder.encode("pass3"), false);
        userJdbcInsert.execute(new BeanPropertySqlParameterSource(user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;

@Repository
@Transactional
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user")
                .usingGeneratedKeyColumns("id");
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, name, created_at, updated_at FROM user",
                                  new DataClassRowMapper<>(User.class));
    }

    public Optional<User> findById(int id) {
        User user = jdbcTemplate.queryForObject(
                "SELECT id, name, created_at, updated_at FROM user WHERE id = ?",
                new DataClassRowMapper<>(User.class),
                id);
        return Optional.ofNullable(user);
    }

    public User insert(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        Number key = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
        user.setId(key.intValue());
        return user;
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET name = ?, updated_at = ? WHERE id = ?",
                                   user.getName(),
                                   LocalDateTime.now(),
                                   user.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}

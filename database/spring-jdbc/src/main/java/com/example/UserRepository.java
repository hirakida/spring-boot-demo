package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, name, created_at, updated_at FROM user",
                                  new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(int id) {
        User user = jdbcTemplate.queryForObject(
                "SELECT id, name, created_at, updated_at FROM user WHERE id = :id",
                Map.of("id", id),
                new BeanPropertyRowMapper<>(User.class));
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

    public User update(User user) {
        user.setUpdatedAt(LocalDateTime.now());

        jdbcTemplate.update("UPDATE user SET name = :name, updated_at = :updatedAt WHERE id = :id",
                            new BeanPropertySqlParameterSource(user));
        return user;
    }

    public int[] batchUpdate(List<User> users) {
        LocalDateTime now = LocalDateTime.now();
        User[] array = users.stream()
                            .peek(user -> user.setUpdatedAt(now))
                            .toArray(User[]::new);
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch((Object[]) array);

        return jdbcTemplate.batchUpdate("UPDATE user SET name = :name, updated_at = :updatedAt WHERE id = :id",
                                        params);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = :id",
                            Map.of(ID, id));
    }
}

package com.example;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public AccountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("account")
                .usingGeneratedKeyColumns("id");
    }

    public List<Account> findAll() {
        String sql = "SELECT id, name FROM account";
        return jdbcTemplate.query(sql, rowMapper());
    }

    public Account findById(int id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "SELECT id, name FROM account WHERE id=:id";
        return jdbcTemplate.queryForObject(sql, param, rowMapper());
    }

    public Account insert(Account account) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(account);
        // set auto increment id
        Number key = jdbcInsert.executeAndReturnKey(param);
        account.setId(key.intValue());
        return account;
    }

    public Account update(Account account) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(account);
        String sql = "UPDATE account SET name=:name WHERE id=:id";
        jdbcTemplate.update(sql, param);
        return account;
    }

    public int[] batchUpdate(List<Account> accounts) {
        SqlParameterSource[] param = SqlParameterSourceUtils.createBatch(accounts.toArray());
        String sql = "UPDATE account SET name=:name WHERE id=:id";
        return jdbcTemplate.batchUpdate(sql, param);
    }

    public void delete(int id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "DELETE FROM account WHERE id=:id";
        jdbcTemplate.update(sql, param);
    }

    private static RowMapper<Account> rowMapper() {
        return (rs, rowNum) -> new Account(rs.getInt("id"),
                                           rs.getString("name"));
    }
}
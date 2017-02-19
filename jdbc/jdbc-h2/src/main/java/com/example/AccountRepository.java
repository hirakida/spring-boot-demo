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

    final NamedParameterJdbcTemplate jdbcTemplate;

    final SimpleJdbcInsert jdbcInsert;

    final RowMapper<Account> userRowMapper;

    public AccountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("account")
                .usingGeneratedKeyColumns("id");
        userRowMapper = (rs, rowNum) -> Account.builder()
                                               .id(rs.getInt("id"))
                                               .name(rs.getString("name"))
                                               .build();
    }

    public List<Account> findAll() {
        String sql = "SELECT id, name FROM account";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public Account findById(int id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "SELECT id,name FROM account WHERE id=:id";
        return jdbcTemplate.queryForObject(sql, param, userRowMapper);
    }

    public Account insert(Account user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        // auto incrementのidを設定
        Number key = jdbcInsert.executeAndReturnKey(param);
        user.setId(key.intValue());
        return user;
    }

    public Account update(Account user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = "UPDATE account SET name=:name WHERE id=:id";
        jdbcTemplate.update(sql, param);
        return user;
    }

    public int[] batchUpdate(List<Account> users) {
        SqlParameterSource[] param = SqlParameterSourceUtils.createBatch(users.toArray());
        String sql = "UPDATE account SET name=:name WHERE id=:id";
        return jdbcTemplate.batchUpdate(sql, param);
    }

    public void delete(int id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = "delete from account where id=:id";
        jdbcTemplate.update(sql, param);
    }
}

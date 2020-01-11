package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRepository {
    public static final String TABLE_NAME = "user_table";
    private final Statement statement;

    public int count() {
        String sql = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME);
        try {
            ResultSet result = statement.executeQuery(sql);
            return result.next() ? result.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        String sql = String.format("SELECT * FROM %s ORDER BY id", TABLE_NAME);
        List<User> users = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                users.add(toUser(result));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(int id) {
        String sql = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);
        try {
            ResultSet result = statement.executeQuery(sql);
            return Optional.ofNullable(result.next() ? toUser(result) : null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(User user) {
        String sql = String.format("INSERT INTO %s VALUES(%d, '%s')", TABLE_NAME, user.getId(), user.getName());
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User toUser(ResultSet result) {
        try {
            return new User(result.getInt(1), result.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

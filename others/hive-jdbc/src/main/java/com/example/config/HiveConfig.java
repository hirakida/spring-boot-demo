package com.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HiveConfig {
    @Bean
    public Connection connection(HiveProperties properties) throws SQLException {
        return DriverManager.getConnection(properties.getUrl());
    }

    @Bean
    public Statement statement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}

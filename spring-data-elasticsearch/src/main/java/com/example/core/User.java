package com.example.core;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Document(indexName = "user")
@JsonNaming(SnakeCaseStrategy.class)
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String message;
    private LocalDateTime createdAt;

    public static User of(String name, String message, LocalDateTime now) {
        User user = new User();
        user.setName(name);
        user.setMessage(message);
        user.setCreatedAt(now);
        return user;
    }
}

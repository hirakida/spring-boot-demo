package com.example.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

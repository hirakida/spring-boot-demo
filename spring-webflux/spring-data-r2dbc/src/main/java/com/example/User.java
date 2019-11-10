package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
    @Id
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}

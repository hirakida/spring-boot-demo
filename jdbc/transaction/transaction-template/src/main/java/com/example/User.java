package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
    @Id
    private int id;
    private String name;
    @CreatedDate
    private LocalDateTime createdAt;
}

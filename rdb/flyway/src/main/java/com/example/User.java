package com.example;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

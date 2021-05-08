package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

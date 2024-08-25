package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class User implements Comparable<User>, Serializable {
    @Id
    private String id;
    private Long userId;
    private String name;
    private LocalDateTime createdAt;

    @Override
    public int compareTo(User user) {
        return userId.compareTo(user.getUserId());
    }
}

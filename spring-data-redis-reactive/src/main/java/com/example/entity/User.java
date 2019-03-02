package com.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class User implements Serializable {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User of(int id, String name, LocalDateTime now) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return user;
    }
}

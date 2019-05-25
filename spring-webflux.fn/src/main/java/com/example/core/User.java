package com.example.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
    @Id
    private String id;
    private String name;
    private int age;

    public static User of(String name, int age) {
        final User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}

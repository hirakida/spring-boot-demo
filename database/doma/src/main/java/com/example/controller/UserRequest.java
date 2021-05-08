package com.example.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.entity.User;

import lombok.Data;

@Data
public class UserRequest {
    @NotNull
    @Size(max = 30)
    private String name;
    @NotNull
    private Integer age;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}

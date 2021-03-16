package com.example.web.model;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.example.entity.Gender;
import com.example.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    private String card;

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}

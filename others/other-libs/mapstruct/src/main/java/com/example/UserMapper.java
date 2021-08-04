package com.example;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserForm form);

    List<User> toUsers(List<UserForm> forms);
}

package com.example.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserRepository userRepository;

    public User user(int id) {
        return userRepository.getOne(id);
    }

    public List<User> users() {
        return userRepository.findAll();
    }
}

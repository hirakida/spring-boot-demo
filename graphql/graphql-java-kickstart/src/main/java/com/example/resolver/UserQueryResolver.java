package com.example.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {
    private final UserRepository userRepository;

    public User user(int id) {
        return userRepository.getById(id);
    }

    public List<User> users() {
        return userRepository.findAll();
    }
}

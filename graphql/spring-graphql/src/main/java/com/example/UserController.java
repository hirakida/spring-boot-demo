package com.example;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User user(@Argument int id) {
        return userRepository.findById(id)
                             .orElseThrow();
    }

    @QueryMapping
    public List<User> userByName(@Argument String name) {
        return userRepository.findByName(name);
    }

    @MutationMapping
    public User createUser(@Argument String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    @MutationMapping
    public void deleteUser(@Argument int id) {
        userRepository.deleteById(id);
    }
}

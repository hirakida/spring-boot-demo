package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import com.example.model.User;
import com.example.repository.UserRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @QueryMapping
    public Optional<User> user(@Argument int id) {
        return userRepository.findById(id);
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public List<User> usersByName(@Argument String name) {
        return userRepository.findByName(name);
    }

    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User user = new User();
        user.setName(input.getName());
        user.setRoleId(input.getRoleId());
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument int id, @Argument UserInput input) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(input.getName());
        user.setRoleId(input.getRoleId());
        return userRepository.save(user);
    }

    @MutationMapping
    public void deleteUser(@Argument int id) {
        userRepository.deleteById(id);
    }

    @Data
    public static class UserInput {
        private String name;
        private Integer roleId;
    }
}

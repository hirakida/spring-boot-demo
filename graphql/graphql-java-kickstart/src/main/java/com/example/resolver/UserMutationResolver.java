package com.example.resolver;

import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {
    private final UserRepository userRepository;

    public User createUser(UserInput input) {
        User user = new User();
        user.setName(input.getName());
        user.setRoleId(input.getRoleId());
        return userRepository.save(user);
    }

    public User updateUser(Integer id, UserInput input) {
        User user = userRepository.getById(id);
        user.setName(input.getName());
        user.setRoleId(input.getRoleId());
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Data
    public static class UserInput {
        private String name;
        private Integer roleId;
    }
}

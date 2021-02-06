package com.example.resolver;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.RoleRepository;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleResolver implements GraphQLResolver<User> {
    private final RoleRepository roleRepository;

    public Optional<Role> role(User user) {
        if (user.getRoleId() == null) {
            return Optional.empty();
        }
        return roleRepository.findAll().stream()
                             .filter(role -> user.getRoleId().equals(role.getId()))
                             .findFirst();
    }
}

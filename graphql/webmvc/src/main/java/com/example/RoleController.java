package com.example;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;

    @SchemaMapping
    public Optional<Role> role(User user) {
        if (user.getRoleId() == null) {
            return Optional.empty();
        }
        return roleRepository.findById(user.getRoleId());
    }
}

package com.example;

import static org.springframework.web.servlet.function.ServerResponse.notFound;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository userRepository;

    public ServerResponse findById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return userRepository.findById(id)
                             .map(user -> ok().body(user))
                             .orElse(notFound().build());
    }

    public ServerResponse findAll(ServerRequest request) {
        return ok().body(userRepository.findAll());
    }
}

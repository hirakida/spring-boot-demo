package com.example;

import static org.springframework.web.servlet.function.ServerResponse.notFound;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class UserHandler {
    private final List<User> users = List.of(new User(1, "name1"),
                                             new User(2, "name2"),
                                             new User(3, "name3"),
                                             new User(4, "name4"),
                                             new User(5, "name5"));

    public ServerResponse findById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return users.stream()
                    .filter(user -> user.id() == id)
                    .findFirst()
                    .map(user -> ok().body(user))
                    .orElse(notFound().build());
    }

    public ServerResponse findAll(ServerRequest request) {
        return ok().body(users);
    }
}

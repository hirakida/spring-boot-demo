package com.example.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rxjava")
@RequiredArgsConstructor
public class RxJavaController {
    private final List<User> users = List.of(new User(1, "name1"),
                                             new User(2, "name2"),
                                             new User(3, "name3"),
                                             new User(4, "name4"),
                                             new User(5, "name5"));

    @GetMapping(path = "/users")
    public Flowable<User> findAll() {
        return Flowable.fromIterable(users);
    }

    @GetMapping("/users/{id}")
    public Single<User> findById(@PathVariable int id) {
        return Flowable.fromIterable(users)
                       .filter(user -> user.id() == id)
                       .firstElement()
                       .switchIfEmpty(Single.error(new ResponseStatusException(NOT_FOUND)));
    }

    @GetMapping(path = "/text_event_stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<String> textEventStream() {
        return Flowable.range(1, 10)
                       .map(i -> "hello" + i)
                       .delay(1000, TimeUnit.MILLISECONDS);
    }

    @GetMapping(path = "/json_stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flowable<Map<String, String>> jsonStream() {
        return Flowable.range(1, 10)
                       .map(i -> Map.of("message", "hello" + i))
                       .delay(1000, TimeUnit.MILLISECONDS);
    }

    public record User(int id, String name) {}
}

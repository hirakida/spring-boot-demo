package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RxJava2Controller {
    private final UserService userService;

    @GetMapping(path = "/users")
    public Flowable<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Single<User> getById(@PathVariable int id) {
        return userService.findById(id)
                          .switchIfEmpty(Single.error(new ResponseStatusException(NOT_FOUND)));
    }

    @GetMapping(path = "/text_event_stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<String> textEventStream() {
        return Flowable.range(1, 10)
                       .map(i -> "hello" + i)
                       .delay(1000, TimeUnit.MILLISECONDS);
    }

    @GetMapping(path = "/json_stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flowable<Map<String, String>> jsonStream() {
        return Flowable.range(1, 10)
                       .map(i -> Map.of("message", "hello" + i))
                       .delay(1000, TimeUnit.MILLISECONDS);
    }
}

package com.example;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.form.PersonForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final MessageSource messageSource;

    @GetMapping
    public List<Person> findAll() {
        return IntStream.rangeClosed(1, 5)
                        .mapToObj(i -> new Person("name" + i, 20 + i))
                        .collect(toList());
    }

    @GetMapping("/search")
    public Person find(@Validated PersonForm form) {
        return toPerson(form);
    }

    @PostMapping
    public Person create(@RequestBody @Validated PersonForm form) {
        return toPerson(form);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Locale locale) {
        e.getBindingResult()
         .getAllErrors().stream()
         .map(error -> messageSource.getMessage(error, locale))
         .forEach(message -> log.warn("{}", message));
    }

    private static Person toPerson(PersonForm form) {
        return new Person(form.getName(), Objects.requireNonNullElse(form.getAge(), 0));
    }
}

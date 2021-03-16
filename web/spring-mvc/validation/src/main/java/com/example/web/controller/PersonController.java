package com.example.web.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.web.controller.form.Person;
import com.example.web.controller.form.PersonParams;
import com.example.validator.PersonValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final PersonValidator validator;
    private final MessageSource messageSource;

    @InitBinder("person")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public List<Person> findAll() {
        return IntStream.rangeClosed(1, 5)
                        .mapToObj(i -> new Person("name" + i, 20 + i))
                        .collect(toList());
    }

    @GetMapping("/search")
    public Person findByParams(@Validated PersonParams params, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            logErrors(result, locale);
            throw new ResponseStatusException(BAD_REQUEST);
        }
        return new Person(params.getName(), Objects.requireNonNullElse(params.getAge(), 0));
    }

    @PostMapping
    public Person create(@RequestBody @Validated Person person, BindingResult result, Locale locale) {
        if (result.hasErrors()) {
            logErrors(result, locale);
            throw new ResponseStatusException(BAD_REQUEST);
        }
        return person;
    }

    private void logErrors(BindingResult result, Locale locale) {
        result.getAllErrors().stream()
              .filter(error -> error.getCode() != null)
              .map(error -> messageSource.getMessage(error.getCode(), null, error.getDefaultMessage(), locale))
              .forEach(message -> log.warn("{}", message));
    }
}

package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        if (id > 10) {
            throw new DataNotFoundException("id:" + id);
        }
        return "index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7720456113930404213L;

    public DataNotFoundException(String message) {
            super(message);
        }
    }
}

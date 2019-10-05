package com.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{statusCode}")
    public String error(@PathVariable int statusCode) {
        throw new ResponseStatusException(HttpStatus.valueOf(statusCode));
    }
}

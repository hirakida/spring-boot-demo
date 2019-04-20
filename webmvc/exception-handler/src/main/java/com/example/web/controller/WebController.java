package com.example.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class WebController {

    @GetMapping("/{statusCode}")
    public String get(@PathVariable int statusCode, Model model) {
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        if (!httpStatus.is2xxSuccessful()) {
            throw new ResponseStatusException(httpStatus);
        }
        model.addAttribute("status", httpStatus);
        return "index";
    }
}

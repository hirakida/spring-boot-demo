package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1") long id) {
        if (id > 10) {
            throw new ResourceAccessException("not found " + id);
        }
        model.addAttribute("message", "index");
        return "index";
    }

    @GetMapping("/include")
    public String include(Model model) {
        model.addAttribute("message", "include");
        return "index";
    }

    @GetMapping("/exclude")
    public String exclude(Model model) {
        model.addAttribute("message", "exclude");
        return "index";
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceAccessException(ResourceAccessException e,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        log.info("WebController::handleResourceAccessException");
        return "404";
    }
}

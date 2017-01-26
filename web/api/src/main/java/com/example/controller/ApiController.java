package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * RestControllerではない場合は、@ResponseBodyが必要
 */
@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @ResponseBody
    @GetMapping
    public String get() {
        return "ok";
    }
}

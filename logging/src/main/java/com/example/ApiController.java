package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/")
    public String log() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "log";
    }
}

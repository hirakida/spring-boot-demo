package com.example;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HelloService {
    public void run() {
        log.info("Hello!");
    }
}

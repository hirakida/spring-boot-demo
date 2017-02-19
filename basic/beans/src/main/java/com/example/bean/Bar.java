package com.example.bean;

import javax.annotation.PostConstruct;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Bar {
    private String name;
    private String message;

    @PostConstruct
    public void PostConstruct() {
        log.info("PostConstruct {}", this);
    }
}

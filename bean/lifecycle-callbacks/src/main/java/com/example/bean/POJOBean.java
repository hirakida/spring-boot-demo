package com.example.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class POJOBean {
    private String name;
    private String message;

    @PostConstruct
    public void postConstruct() {
        log.info("PostConstruct {}", this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy {}", this);
    }

    // Called after PostConstruct
    public void init() {
        log.info("init {}", this);
    }

    // Called after PreDestroy
    public void destroy() {
        log.info("destroy {}", this);
    }
}

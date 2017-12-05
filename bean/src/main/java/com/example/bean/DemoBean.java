package com.example.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DemoBean {
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

    // PostConstructの後で呼ばれる
    public void init() {
        log.info("init {}", this);
    }

    // PreDestroyの後で呼ばれる
    public void destroy() {
        log.info("destroy {}", this);
    }
}

package com.example.bean;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Foo {
    private String name;
    private String message;
    private LocalDateTime dateTime;

    @PostConstruct
    public void PostConstruct() {
        log.info("PostConstruct {}", this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");
    }

    /**
     * @Bean のinitMethodに指定している
     * PostConstructの後で呼ばれる
     */
    public void init() {
        log.info("init");
    }

    /**
     * @Bean のdestroyMethodに指定している
     * PreDestroyの後で呼ばれる
     */
    public void destroy() {
        log.info("destroy");
    }
}

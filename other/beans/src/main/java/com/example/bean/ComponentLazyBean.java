package com.example.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Lazy   // これを付けると参照する直前に生成される
@Slf4j
public class ComponentLazyBean {
    @Getter
    private String name;
    @Getter
    private String message;

    // 参照する直前に呼ばれる
    @PostConstruct
    public void init() {
        name = "lazyBean";
        message = "@Component + @Lazy";
        log.info("init");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");
    }
}

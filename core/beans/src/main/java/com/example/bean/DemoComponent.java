package com.example.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoComponent implements InitializingBean, DisposableBean {
    @Getter
    private String name;
    @Getter
    private String message;

    @PostConstruct
    public void init() {
        name = "demoComponent";
        message = "@Component";
        log.info("PostConstruct");
    }

    // PostConstructの後で呼ばれる
    @Override
    public void afterPropertiesSet() {
        log.info("afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");
    }

    // PreDestroyの後で呼ばれる
    @Override
    public void destroy() {
        log.info("destroy");
    }
}

package com.example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        log.info("2 afterPropertiesSet: {}", this);
    }

    @Override
    public void destroy() {
        log.info("5 destroy: {}", this);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("1 PostConstruct: {}", this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("4 PreDestroy: {}", this);
    }

    public void initMethod() {
        log.info("3 initMethod: {}", this);
    }

    public void destroyMethod() {
        log.info("6 destroyMethod: {}", this);
    }
}

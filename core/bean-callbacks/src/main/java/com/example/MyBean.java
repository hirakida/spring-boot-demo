package com.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBean implements InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        log.info("1 PostConstruct: {}", this);
    }

    @Override
    public void afterPropertiesSet() {
        log.info("2 afterPropertiesSet: {}", this);
    }

    public void initMethod() {
        log.info("3 initMethod: {}", this);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("4 PreDestroy: {}", this);
    }

    @Override
    public void destroy() {
        log.info("5 destroy: {}", this);
    }

    public void destroyMethod() {
        log.info("6 destroyMethod: {}", this);
    }
}

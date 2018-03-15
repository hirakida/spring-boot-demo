package com.example.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BeanImpl implements InitializingBean, DisposableBean {
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
    @Override
    public void afterPropertiesSet() {
        log.info("afterPropertiesSet {}", this);
    }

    // Called after PreDestroy
    @Override
    public void destroy() {
        log.info("destroy {}", this);
    }
}

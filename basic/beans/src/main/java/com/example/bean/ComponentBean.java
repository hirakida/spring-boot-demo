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
public class ComponentBean implements InitializingBean, DisposableBean {
    @Getter
    private String name;
    @Getter
    private String message;

    /**
     * インジェクションされたあとに呼ばれる
     */
    @PostConstruct
    public void init() {
        name = "ComponentBean";
        message = "@Component";
        log.info("PostConstruct");
    }

    /**
     * インスタンスが破棄されるタイミングで呼ばれる
     */
    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");
    }

    /**
     * PostConstructの後で呼ばれる
     */
    @Override
    public void afterPropertiesSet() {
        log.info("afterPropertiesSet");
    }

    /**
     * PreDestroyの後で呼ばれる
     */
    @Override
    public void destroy() {
        log.info("destroy");
    }
}

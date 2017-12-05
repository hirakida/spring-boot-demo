package com.example.demo.core;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Listenerは以下のどちらかの方法で実装する
 * - メソッドに@EventListenerを付ける
 * - ApplicationListener interfaceを実装する
 */
@Component
@Slf4j
public class AppEventListener {

    // 自分で作成したevent
    @EventListener
    public void onApplicationEvent(AccountEvent event) {
        log.info("AppEventListener::AccountEvent AccountEvent={}", event.getSource());
    }

    /**
     * spring bootのevent
     */
    @EventListener
    public void onApplicationEvent(ApplicationStartingEvent event) {
        log.info("ApplicationStartedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("ApplicationPreparedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("ApplicationReadyEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("ApplicationEnvironmentPreparedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("SpringApplicationEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ApplicationFailedEvent event) {
        log.info("ApplicationFailedEvent={}", event.getSource());
    }

    /**
     * spring contextのevent
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        log.info("ContextStartedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.info("ContextStoppedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("ContextClosedEvent={}", event.getSource());
    }

    /**
     * spring webのevent
     */
    @EventListener
    public void onApplicationEvent(RequestHandledEvent event) {
        log.info("RequestHandledEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        log.info("ServletRequestHandledEvent={}", event.getSource());
    }
}
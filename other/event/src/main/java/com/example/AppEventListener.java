package com.example;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
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

    // springで提供されているevent
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("ContextRefreshedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("ContextClosedEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(RequestHandledEvent event) {
        log.info("RequestHandledEvent={}", event.getSource());
    }

    @EventListener
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        log.info("ServletRequestHandledEvent={}", event.getSource());
    }

    // 自分で作成したevent
    @EventListener
    public void onApplicationEvent(AccountEvent event) {
        log.info("EventListener AccountEvent={}", event.getSource());
    }

    @Component
    public static class AccountListener implements ApplicationListener<AccountEvent> {

        @Override
        public void onApplicationEvent(AccountEvent event) {
            log.info("ApplicationListener event={}", event.getSource());
        }
    }
}

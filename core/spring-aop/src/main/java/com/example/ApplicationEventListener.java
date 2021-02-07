package com.example;

import java.util.stream.IntStream;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.aop.MethodInterceptorImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodInterceptorImpl());
        proxyFactory.setTarget(userService);
        UserService proxy = (UserService) proxyFactory.getProxy();

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> proxy.insert("name" + i));
    }
}

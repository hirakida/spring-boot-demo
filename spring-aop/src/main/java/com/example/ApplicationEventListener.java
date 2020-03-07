package com.example;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.aop.MethodInterceptorImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
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
        List<User> users = proxy.findAll();
        log.info("{}", users);
    }
}

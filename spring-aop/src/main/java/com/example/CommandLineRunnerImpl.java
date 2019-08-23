package com.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodInterceptorImpl());
        proxyFactory.setTarget(userService);
        this.userService = (UserService) proxyFactory.getProxy();
    }

    @Override
    public void run(String... args) {
        log.info("{}", userService.findAll());
    }

    public static class MethodInterceptorImpl implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("{}", invocation);
            return invocation.proceed();
        }
    }
}

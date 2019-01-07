package com.example.controller;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodInterceptorImpl());
        proxyFactory.setTarget(userService);
        this.userService = (UserService) proxyFactory.getProxy();
    }

    @GetMapping("/users")
    public List<User> findAll() {
        log.info("findAll");
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable long id) {
        log.info("findById id={}", id);
        return userService.findById(id);
    }

    public static class MethodInterceptorImpl implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("{}", invocation);
            return invocation.proceed();
        }
    }
}

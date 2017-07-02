package com.example;

import java.io.IOException;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.config.MethodInterceptorImpl;
import com.example.domain.AccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    private final ApplicationContext context;

    private final AccountService accountService;

    private final MethodInterceptorImpl methodInterceptor;

    @Override
    public void run(String... strings) throws IOException {
        log.info("run start");

        // ProxyFactory
        ProxyFactory proxyFactory = new ProxyFactory(accountService);
        proxyFactory.addAdvice(methodInterceptor);
        AccountService service1 = (AccountService) proxyFactory.getProxy();
        service1.getAccount(1);

        // ProxyFactoryBean
        AccountService service2 = context.getBean("proxyAccountService", AccountService.class);
        service2.getAccount(2);

        log.info("run end");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

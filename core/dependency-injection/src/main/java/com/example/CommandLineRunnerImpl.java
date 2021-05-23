package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.example.bean.Bar;
import com.example.bean.Foo;
import com.example.bean.SecondaryBean;
import com.example.config.BeanConfig;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ApplicationContext context;
    private final ApplicationContext xmlContext;

    // Constructor injection
    private final Foo foo;
    private final Foo foo1;
    private final Foo foo2;

    // Setter injection
    private Foo foo3;

    // Field injection
    @Qualifier("bar1")
    @Autowired
    private Bar bar1;
    @Autowired
    private Bar bar2;

    public CommandLineRunnerImpl(Foo foo, Foo foo1, @Qualifier("foo2") Foo foo2) {
        context = new AnnotationConfigApplicationContext(BeanConfig.class);
        xmlContext = new ClassPathXmlApplicationContext("bean.xml");
        this.foo = foo;
        this.foo1 = foo1;
        this.foo2 = foo2;
        log("Constructor");
    }

    @Autowired
    public void setFoo3(@SecondaryBean Foo foo3) {
        this.foo3 = foo3;
        log.info("foo3: {}", foo3);
    }

    @PostConstruct
    public void init() {
        log("PostConstruct");
    }

    @Override
    public void run(String... args) {
        log("run");
        log.info("foo4: {}", context.getBean("foo4"));
        log.info("foo5: {}", xmlContext.getBean(Foo.class));
    }

    private void log(String msg) {
        log.info("{} foo={} foo1={} foo2={} foo3={} bar1={} bar2={}", msg, foo, foo1, foo2, foo3, bar1, bar2);
    }
}

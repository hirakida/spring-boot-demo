package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.bean.Bar;
import com.example.bean.Foo;
import com.example.bean.SecondlyBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    @Primary
    public Foo foo1() {
        Foo bean = new Foo();
        bean.setName("foo1");
        bean.setMessage("@Bean, @Primary");
        log.info("{}", bean);
        return bean;
    }

    @Bean
    @DependsOn({ "foo1", "foo3" })
    public Foo foo2() {
        Foo bean = new Foo();
        bean.setName("foo2");
        bean.setMessage("@Bean");
        log.info("{}", bean);
        return bean;
    }

    @Bean
    @DependsOn("foo1")
    @SecondlyBean
    public Foo foo3() {
        Foo bean = new Foo();
        bean.setName("foo3");
        bean.setMessage("@Bean");
        log.info("{}", bean);
        return bean;
    }

    @Bean
    @Lazy
    public Foo foo4() {
        Foo bean = new Foo();
        bean.setName("foo4");
        bean.setMessage("@Bean, @Lazy");
        log.info("{}", bean);
        return bean;
    }

    @Bean
    public Bar bar1() {
        Bar bean = new Bar();
        bean.setName("bar1");
        bean.setMessage("@Bean");
        log.info("{}", bean);
        return bean;
    }

    @Bean
    @Primary
    public Bar bar2() {
        Bar bean = new Bar();
        bean.setName("bar2");
        bean.setMessage("@Bean, @Primary");
        log.info("{}", bean);
        return bean;
    }
}

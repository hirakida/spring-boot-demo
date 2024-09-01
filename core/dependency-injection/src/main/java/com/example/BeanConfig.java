package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.bean.Bar;
import com.example.bean.Foo;
import com.example.bean.SecondaryBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BeanConfig {
    @Bean
    @Primary
    public Foo foo1() {
        Foo bean = new Foo("foo1", "@Bean, @Primary");
        log.info("foo1: {}", bean);
        return bean;
    }

    @Bean
    @DependsOn({ "foo1", "foo3" })
    public Foo foo2() {
        Foo bean = new Foo("foo2", "@Bean");
        log.info("foo2: {}", bean);
        return bean;
    }

    @Bean
    @DependsOn("foo1")
    @SecondaryBean
    public Foo foo3() {
        Foo bean = new Foo("foo3", "@Bean");
        log.info("foo3: {}", bean);
        return bean;
    }

    @Bean
    @Lazy
    public Foo foo4() {
        Foo bean = new Foo("foo4", "@Bean, @Lazy");
        log.info("foo4: {}", bean);
        return bean;
    }

    @Bean
    public Bar bar1() {
        Bar bean = new Bar("bar1", "@Bean");
        log.info("bar1: {}", bean);
        return bean;
    }

    @Bean
    @Primary
    public Bar bar2() {
        Bar bean = new Bar("bar2", "@Bean, @Primary");
        log.info("bar2: {}", bean);
        return bean;
    }
}

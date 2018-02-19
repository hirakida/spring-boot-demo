package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.bean.Bar;
import com.example.bean.Foo;

@Configuration
public class BeanConfig {

    @Bean
    @Primary
    public Foo foo1() {
        Foo bean = new Foo();
        bean.setName("foo1");
        bean.setMessage("@Bean + @Primary");
        return bean;
    }

    @Bean
    public Foo foo2() {
        Foo bean = new Foo();
        bean.setName("foo2");
        bean.setMessage("@Bean");
        return bean;
    }

    @Bean
    public Foo foo3() {
        Foo bean = new Foo();
        bean.setName("foo3");
        bean.setMessage("@Bean");
        return bean;
    }

    @Bean
    public Bar bar1() {
        Bar bean = new Bar();
        bean.setName("bar1");
        bean.setMessage("@Bean");
        return bean;
    }

    @Bean
    @Primary
    public Bar bar2() {
        Bar bean = new Bar();
        bean.setName("bar2");
        bean.setMessage("@Bean + @Primary");
        return bean;
    }
}

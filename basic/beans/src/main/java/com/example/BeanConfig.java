package com.example;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.bean.Bar;
import com.example.bean.Baz;
import com.example.bean.Foo;
import com.example.bean.Qux;

@Configuration
public class BeanConfig {

    /**
     * @Bean メソッド名がBean名、戻り値がBeanのインスタンスとして定義される
     * initとdestroy methodを指定できる
     * @Primary injection可能なclassが複数ある場合に優先させる
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Primary
    public Foo foo1() {
        Foo bean = new Foo();
        bean.setName("foo1");
        bean.setMessage("@Bean + @Primary");
        bean.setDateTime(LocalDateTime.now());
        return bean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Foo foo2() {
        Foo bean = new Foo();
        bean.setName("foo2");
        bean.setMessage("@Bean");
        bean.setDateTime(LocalDateTime.now());
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

    @Bean
    public Baz baz1() {
        Baz bean = new Baz();
        bean.setName("baz1");
        bean.setMessage("@Bean");
        return bean;
    }

    @Bean
    @Lazy   // 参照する直前に生成される
    public Qux qux1() {
        Qux bean = new Qux();
        bean.setName("qux1 lazy");
        bean.setMessage("@Bean + @Lazy");
        return bean;
    }
}

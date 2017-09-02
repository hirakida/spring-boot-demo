package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.bean.DemoBean;

@Configuration
public class BeanConfig {

    /**
     * メソッド名がBean名、戻り値がBeanのインスタンスとして定義される
     * initとdestroy methodを指定できる
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Primary
    public DemoBean demoBean1() {
        DemoBean bean = new DemoBean();
        bean.setName("demo1");
        bean.setMessage("@Bean + @Primary");
        return bean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Lazy   // 参照する直前に生成される
    @LazyBean
    public DemoBean demoBean2() {
        DemoBean bean = new DemoBean();
        bean.setName("demo2");
        bean.setMessage("@Bean + @Lazy");
        return bean;
    }
}

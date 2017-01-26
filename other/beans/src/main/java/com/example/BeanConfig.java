package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bean.DataBean;

@Configuration
public class BeanConfig {

    /**
     * メソッド名がBean名、戻り値がBeanのインスタンスとして定義される
     * initとdestroy methodを指定できる
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DataBean dataBean1() {
        DataBean bean = new DataBean();
        bean.setName("dataBean");
        bean.setMessage("@Bean 1");
        return bean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DataBean dataBean2() {
        DataBean bean = new DataBean();
        bean.setName("dataBean");
        bean.setMessage("@Bean 2");
        return bean;
    }
}

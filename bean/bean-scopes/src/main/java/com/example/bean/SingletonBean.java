package com.example.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
//@Scope(BeanDefinition.SCOPE_SINGLETON)
@Data
public class SingletonBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}


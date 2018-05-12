package com.example.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
public class PrototypeBean {
    private long id;
    private String name;
    private long count;

    public long getCount() {
        return ++count;
    }
}
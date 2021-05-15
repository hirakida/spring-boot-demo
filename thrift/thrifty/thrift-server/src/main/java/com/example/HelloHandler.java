package com.example;

import org.springframework.stereotype.Component;

import com.example.thrift.Hello;

@Component
public class HelloHandler implements Hello.Iface {
    @Override
    public String hello() {
        return "Hello!";
    }
}

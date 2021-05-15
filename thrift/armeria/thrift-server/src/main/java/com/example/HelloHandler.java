package com.example;

import org.apache.thrift.async.AsyncMethodCallback;
import org.springframework.stereotype.Component;

import com.example.thrift.Hello;

@Component
public class HelloHandler implements Hello.AsyncIface {
    @Override
    public void hello(AsyncMethodCallback<String> resultHandler) {
        resultHandler.onComplete("Hello!");
    }
}

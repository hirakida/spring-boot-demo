package com.example;

public interface CountMBean {
    long getCount();

    void increment();

    void decrement();

    String getName();

    void setName(String name);
}

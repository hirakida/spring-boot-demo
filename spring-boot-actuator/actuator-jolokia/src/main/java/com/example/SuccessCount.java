package com.example;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example:name=SuccessCount,type=Count")
public class SuccessCount implements CountMBean {
    private final AtomicLong count = new AtomicLong();
    private final AtomicReference<String> name = new AtomicReference<>();

    @ManagedAttribute(description = "The success count")
    @Override
    public long getCount() {
        return count.get();
    }

    @ManagedOperation(description = "Increment the success count")
    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @ManagedOperation(description = "Decrement the success count")
    @Override
    public void decrement() {
        count.decrementAndGet();
    }

    @ManagedAttribute(description = "The name")
    @Override
    public String getName() {
        return name.get();
    }

    @ManagedOperation(description = "Set the name")
    @ManagedOperationParameters(@ManagedOperationParameter(name = "name", description = "The name"))
    @Override
    public void setName(String name) {
        this.name.set(name);
    }
}

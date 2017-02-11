package com.example;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(final String message) throws Exception {
        return message + "!!";
    }
}

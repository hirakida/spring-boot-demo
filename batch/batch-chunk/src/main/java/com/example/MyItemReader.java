package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyItemReader implements ItemReader<String> {

    private static final List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    private static int index = 0;

    @Override
    public String read() throws Exception {
        if (list.size() <= index) {
            index = 0;
            return null;
        }
        return list.get(index++);
    }
}

package com.example;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        log.info("{}", list);
    }
}

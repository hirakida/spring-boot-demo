package com.example.config;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class PartitionerImpl implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        return IntStream.rangeClosed(1, gridSize)
                        .mapToObj(i -> "partition" + i)
                        .collect(Collectors.toMap(name -> name,
                                                  name -> {
                                                      ExecutionContext context = new ExecutionContext();
                                                      context.putString("name", name);
                                                      return context;
                                                  }));
    }
}

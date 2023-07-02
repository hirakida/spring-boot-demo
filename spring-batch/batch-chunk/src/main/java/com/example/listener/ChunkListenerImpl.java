package com.example.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChunkListenerImpl implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext context) {
        log.info("BeforeChunk {}", context);
    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.info("AfterChunk {}", context);
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.info("AfterChunkError {}", context);
    }
}

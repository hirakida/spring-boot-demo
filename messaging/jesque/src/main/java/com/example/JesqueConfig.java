package com.example;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientPoolImpl;
import net.greghaines.jesque.utils.PoolUtils;
import net.greghaines.jesque.worker.MapBasedJobFactory;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerEvent;
import net.greghaines.jesque.worker.WorkerImpl;
import net.greghaines.jesque.worker.WorkerListener;

import com.example.job.Job1;
import com.example.job.Job2;
import com.example.job.Job3;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JesqueConfig {

    public static final String QUEUE_NAME = "queue1";

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.database}")
    private int database;

    ExecutorService executor;

    WorkerImpl worker;

    @PostConstruct
    public void init() {
        log.info("PostConstruct");
        executor = Executors.newSingleThreadExecutor();
        worker = new WorkerImpl(config(),
                                Collections.singletonList(QUEUE_NAME),
                                new MapBasedJobFactory(map(
                                        entry("Job1", Job1.class),
                                        entry("Job2", Job2.class),
                                        entry("Job3", Job3.class))));
        worker.getWorkerEventEmitter()
              .addListener(new WorkerListenerImpl(), WorkerEvent.WORKER_ERROR);
        worker.getWorkerEventEmitter()
              .addListener(new WorkerListenerImpl(), WorkerEvent.JOB_FAILURE);
        executor.execute(worker);
    }

    @PreDestroy
    public void destroy() {
        log.info("PreDestroy");
        worker.end(true);
        executor.shutdown();
    }

    @Bean
    public Client client() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(1);
        poolConfig.setMaxTotal(10);
        return new ClientPoolImpl(config(), PoolUtils.createJedisPool(config(), poolConfig));
    }

    @Bean
    public Config config() {
        return new ConfigBuilder().withHost(host)
                                  .withPort(port)
                                  .withDatabase(database)
                                  .build();
    }

    public static class WorkerListenerImpl implements WorkerListener {
        @Override
        public void onEvent(WorkerEvent event, Worker worker, String queue, Job job,
                            Object runner, Object result, Throwable t) {
            log.info("{} {} {} {}", event, worker, queue, job, t);
        }
    }
}

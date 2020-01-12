package com.example.config;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientPoolImpl;
import net.greghaines.jesque.utils.PoolUtils;
import net.greghaines.jesque.worker.MapBasedJobFactory;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerImpl;

import com.example.job.AppJob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JesqueConfig {
    public static final String QUEUE_NAME = "queue1";
    private final RedisProperties redisProperties;

    @PostConstruct
    public void init() {
        taskExecutor().execute(worker());
    }

    @PreDestroy
    public void close() {
        worker().end(true);
    }

    @Bean(destroyMethod = "end")
    public Client client() {
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxTotal(8);
        Pool<Jedis> jedisPool = PoolUtils.createJedisPool(config(), poolConfig);
        return new ClientPoolImpl(config(), jedisPool);
    }

    @Bean
    public Worker worker() {
        MapBasedJobFactory jobFactory =
                new MapBasedJobFactory(map(entry(AppJob.JOB1.getName(), AppJob.JOB1.getJob()),
                                           entry(AppJob.JOB2.getName(), AppJob.JOB2.getJob()),
                                           entry(AppJob.JOB3.getName(), AppJob.JOB3.getJob())));
        Worker worker = new WorkerImpl(config(), List.of(QUEUE_NAME), jobFactory);
        worker.getWorkerEventEmitter().addListener(new WorkerListenerImpl());
        worker.setExceptionHandler(new WorkerExceptionHandler());
        return worker;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(5);
        executor.setAwaitTerminationSeconds(5);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    private Config config() {
        return new ConfigBuilder().withHost(redisProperties.getHost())
                                  .withPort(redisProperties.getPort())
                                  .withDatabase(redisProperties.getDatabase())
                                  .build();
    }
}

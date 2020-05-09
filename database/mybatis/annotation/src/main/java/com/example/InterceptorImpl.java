package com.example;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class,
                method = "update",
                args = { MappedStatement.class, Object.class })
})
@Slf4j
public class InterceptorImpl implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("method: {}", invocation.getMethod());
        log.info("args: {}", invocation.getArgs());
        Object object = invocation.proceed();
        log.info("{}", object);
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

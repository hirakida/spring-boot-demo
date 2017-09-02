package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 正常系
 * Around(before) -> Before -> target method -> Around(after) -> After -> AfterReturning
 * 異常系
 * Around(before) -> Before -> target method -> Around(after) -> After -> AfterThrowing
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Around
     */
    @Around("controller()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[Around(before)] Signature={}", joinPoint.getSignature());
        try {
            // 対象メソッドを実行する
            Object result = joinPoint.proceed();
            log.info("[Around(after)] Signature={} return={}", joinPoint.getSignature(), result);
            return result;
        } catch (Exception e) {
            log.error("[Around] error:", e);
            throw e;
        }
    }

    /**
     * Before
     */
    @Before("execution(* com.example.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("[Before] Signature={}", joinPoint.getSignature());
    }

    // メソッド名がgetで始まる
    @Before("execution(* com.example.service.*.get*(..))")
    public void beforeService1(JoinPoint joinPoint) {
        log.info("[Before]1 Signature={}", joinPoint.getSignature());
    }

    // メソッド名の後がAccount
    @Before("execution(* com.example.service.*.*Account(..))")
    public void beforeService2(JoinPoint joinPoint) {
        log.info("[Before]2 Signature={}", joinPoint.getSignature());
    }

    // 戻り値がAccount
    @Before("execution(com.example.Account com.example.service.*.*(..))")
    public void beforeService3(JoinPoint joinPoint) {
        log.info("[Before]3 Signature={}", joinPoint.getSignature());
    }

    // 引数がlong
    @Before("execution(* com.example.service.*.*(long))")
    public void beforeService4(JoinPoint joinPoint) {
        log.info("[Before]4 Signature={}", joinPoint.getSignature());
    }

    /**
     * After
     */
    @After("controller()")
    public void after(JoinPoint joinPoint) {
        log.info("[After] Signature={}", joinPoint.getSignature());
    }

    /**
     * AfterReturning
     */
    @AfterReturning("controller()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("[AfterReturning] Signature={}", joinPoint.getSignature());
    }

    /**
     * AfterThrowing
     * 例外のthrowを抑止することはできない
     * 特定の例外に対して、一律で例外を変換したいときに便利
     */
    @AfterThrowing(value = "controller()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) throws Exception {
        log.info("[AfterThrowing] Signature={} {}", joinPoint.getSignature(), e);
        throw new RuntimeException(e);
    }

    @Pointcut("within(com.example.controller..*)")
    private void controller() {
    }
}

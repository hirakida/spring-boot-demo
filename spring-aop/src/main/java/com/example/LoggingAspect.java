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
 * normal   - Around(before) -> Before -> target method -> Around(after) -> After -> AfterReturning
 * abnormal - Around(before) -> Before -> target method -> Around(after) -> After -> AfterThrowing
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
        log.info("[Around][before] Signature={}", joinPoint.getSignature());
        try {
            Object result = joinPoint.proceed();
            log.info("[Around][after] Signature={} return={}", joinPoint.getSignature(), result);
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
        log.info("[Before][* com.example.controller.*.*(..)] Signature={}",
                 joinPoint.getSignature());
    }

    // The method name is findXxxx.
    @Before("execution(* com.example.service.*.find*(..))")
    public void beforeService1(JoinPoint joinPoint) {
        log.info("[Before][* com.example.service.*.find*(..)] Signature={}",
                 joinPoint.getSignature());
    }

    // The method name is xxxxById.
    @Before("execution(* com.example.service.*.*ById(..))")
    public void beforeService2(JoinPoint joinPoint) {
        log.info("[Before][* com.example.service.*.*ById(..)] Signature={}",
                 joinPoint.getSignature());
    }

    // The return value is Account.
    @Before("execution(com.example.entity.Account com.example.service.*.*(..))")
    public void beforeService3(JoinPoint joinPoint) {
        log.info("[Before][com.example.entity.Account com.example.service.*.*(..)] Signature={}",
                 joinPoint.getSignature());
    }

    // The argument is long.
    @Before("execution(* com.example.service.*.*(long))")
    public void beforeService4(JoinPoint joinPoint) {
        log.info("[Before][* com.example.service.*.*(long)] Signature={}",
                 joinPoint.getSignature());
    }

    /**
     * After
     */
    @After("controller()")
    public void afterController(JoinPoint joinPoint) {
        log.info("[After][controller] Signature={}", joinPoint.getSignature());
    }

    @After("service()")
    public void afterService(JoinPoint joinPoint) {
        log.info("[After][service] Signature={}", joinPoint.getSignature());
    }

    /**
     * AfterReturning
     */
    @AfterReturning("controller()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("[AfterReturning][controller] Signature={}", joinPoint.getSignature());
    }

    /**
     * AfterThrowing
     */
    @AfterThrowing(value = "controller()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) throws Exception {
        log.error("[AfterThrowing] Signature={}", joinPoint.getSignature(), e);
        throw new RuntimeException(e);
    }

    @Pointcut("within(com.example.controller..*)")
    private void controller() {
    }

    @Pointcut("within(com.example.service..*)")
    private void service() {
    }
}

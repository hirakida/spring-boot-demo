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
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Around(before) -> Before -> target method -> Around(after) -> After -> AfterReturning(AfterThrowing)
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
    @Before("bean(*Controller)")
    public void beforeController(JoinPoint joinPoint) {
        log.info("[Before][controller] Signature={}", joinPoint.getSignature());
    }

    @Before("@annotation(getMapping)")
    public void beforeGetMapping(JoinPoint joinPoint, GetMapping getMapping) {
        log.info("[Before][GetMapping] Signature={} {}", joinPoint.getSignature(), getMapping);
    }

    @Before("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void beforeDeleteMapping(JoinPoint joinPoint) {
        log.info("[Before][DeleteMapping] Signature={}", joinPoint.getSignature());
    }

    // method name = findXxxx
    @Before("execution(* com.example.*Service.find*(..))")
    public void beforeService1(JoinPoint joinPoint) {
        log.info("[Before][* com.example.*Service.find*(..)] Signature={}", joinPoint.getSignature());
    }

    // method name = xxxxById
    @Before("execution(* com.example.*Service.*ById(..))")
    public void beforeService2(JoinPoint joinPoint) {
        log.info("[Before][* com.example.*Service.*ById(..)] Signature={}", joinPoint.getSignature());
    }

    // return value = User
    @Before("execution(com.example.User com.example.*Service.*(..))")
    public void beforeService3(JoinPoint joinPoint) {
        log.info("[Before][com.example.User com.example.*Service.*(..)] Signature={}",
                 joinPoint.getSignature());
    }

    // argument = long
    @Before("execution(* com.example.*Service.*(long))")
    public void beforeService4(JoinPoint joinPoint) {
        log.info("[Before][* com.example.*Service.*(long)] Signature={}", joinPoint.getSignature());
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
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void controller() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void service() {
    }
}

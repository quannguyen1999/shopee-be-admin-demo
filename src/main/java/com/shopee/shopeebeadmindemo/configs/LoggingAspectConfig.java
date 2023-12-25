package com.shopee.shopeebeadmindemo.configs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspectConfig {
    @Pointcut("@annotation(LogNameMethod)")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void logAllMethodBeforeCallsAdvice(JoinPoint joinPoint) {
        log.info("Call: " + joinPoint.getSignature().toShortString() + " start");
    }

    @After("logPointcut()")
    public void logAllMethodAfterCallsAdvice(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toShortString() + " end");
    }
}

package com.shopee.shopeebeadmindemo.configs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Profile("dev")
public class LoggingAspectConfig {
//    @Pointcut("@annotation(LogNameMethod)")
//    public void logPointcut() {
//    }

    //    @Before("logPointcut()")
    @Before("execution(* com.shopee.shopeebeadmindemo.controllers..*(..))")
    public void logAllMethodBeforeCallsAdvice(JoinPoint joinPoint) {
        log.info("Call: " + joinPoint.getSignature().toShortString() + " start");
    }

    //    @After("logPointcut()")
    @After("execution(* com.shopee.shopeebeadmindemo.controllers..*(..))")
    public void logAllMethodAfterCallsAdvice(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toShortString() + " end");
    }
}

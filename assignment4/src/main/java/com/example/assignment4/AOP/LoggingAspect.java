package com.example.assignment4.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("com.example.assignment4.AOP.PointCuts.thisAuthorService()")
    public void logBeforeService(JoinPoint joinPoint){
        log.warn("this Impl before advice: " + joinPoint.getSignature());
    }

    @After("com.example.assignment4.AOP.PointCuts.thisAuthorService()")
    public void logAfterService(JoinPoint joinPoint){
        log.warn("this Impl after advice: " + joinPoint.getSignature());
    }
}

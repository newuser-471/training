package com.example.assignment4.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("this(com.example.assignment4.service.impl.AuthorServiceImpl)")
    public void thisAuthorService(){}
}

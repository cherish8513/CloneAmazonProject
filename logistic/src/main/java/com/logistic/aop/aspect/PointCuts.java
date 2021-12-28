package com.logistic.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("execution(* com.logistic..*(..))")
    private void allSystem(){}

    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Pointcut("execution(* *..*Repository.*(..))")
    private void allRepository(){}

    @Pointcut("execution(* *..*Controller.*(..))")
    private void allController(){}

    @Pointcut("allSystem() && (allService() || allRepository() || allController())")
    public void allAccess(){}
}

package com.amazonClone.logisticSystem.aop.aspect;

import com.amazonClone.logisticSystem.aop.logtrace.LogTrace;
import com.amazonClone.logisticSystem.aop.logtrace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("com.amazonClone.logisticSystem.aop.aspect.PointCuts.allAccess()")
    public Object doTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try{
            String message = joinPoint.getSignature().toShortString();
            status  = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}

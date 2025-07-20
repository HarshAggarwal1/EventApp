package com.example.eventapp.aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* com.example.eventapp.service.*.*(..))")
    public Object logTime(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object ret = jp.proceed();
        long end = System.currentTimeMillis();
        System.out.println(jp.getSignature() + " executed in " + (end - start) + "ms");
        return ret;
    }
}

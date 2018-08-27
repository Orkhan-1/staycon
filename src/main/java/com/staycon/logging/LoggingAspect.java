package com.staycon.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    @Before("allMethods ()")
    public void advice (JoinPoint joinPoint) {
        System.out.println(joinPoint.toString());
    }

    @Pointcut ("execution(public * *(..))")
    public void allMethods () {

    }

    @After("args (name)")
    public void after (String name) {
        System.out.println("After " + name);
    }

    @AfterReturning(pointcut = "args (name)", returning = "returnValue")
    public void afterReturning (String name, Object returnValue) {
        System.out.println("After Returning" + name);
        System.out.println("Output Value" + returnValue.toString());
    }

    @AfterThrowing(pointcut = "args (name)", throwing = "excp")
    public void afterThrowing (String name, RuntimeException excp) {
        System.out.println("After Throwing" + excp);
    }

    @Around ("allMethods ()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) {

        Object returnValue=null;

        try {
            System.out.println("Before");
            returnValue = proceedingJoinPoint.proceed();
            System.out.println("After");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return returnValue;
    }

    @Around ("@annotation(com.staycon.logging.Loggible)")
    public Object customAnnotation (ProceedingJoinPoint proceedingJoinPoint) {

        Object returnValue=null;

        try {
            System.out.println("Before");
            returnValue = proceedingJoinPoint.proceed();
            System.out.println("After");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return returnValue;
    }
}
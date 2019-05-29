package com.steven.SpringDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
interface Performance{
    void perform();
}

@Aspect
class Audience{

    @Pointcut("execution(* com.steven.SpringDemo.Performance.perform( .. ))")
    public void performance(){}

    @Before("performance()")
    public void silenceCellPones(){
        System.out.println("Silencing cell phones");
    }

    @AfterReturning("performance()")
    public void applause(){
        System.out.println("Clasp");
    }

    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("refund");
    }

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("silencing");
            System.out.println("taking seats");
            joinPoint.proceed();
            System.out.println("clap");
        }catch (Throwable e){
            System.out.println("refund refund");
        }
    }
}


public class AOPDemo {
}

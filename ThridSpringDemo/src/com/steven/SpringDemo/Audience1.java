package com.steven.SpringDemo;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience1 {

    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    public void takeSeats() {
        System.out.println("Taking seats");
    }

    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    public void demandRefund() {
        System.out.println("Demand a refund");
    }

    public void watchPerformance(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("silencing");
            System.out.println("taking seats");
            joinPoint.proceed();  //执行被通知的方法, Performance.perform()
            System.out.println("clap1");
        }catch (Throwable e){
            System.out.println("refund refund");
        }
    }
}

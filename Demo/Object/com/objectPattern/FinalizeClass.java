package com.objectPattern;

public class FinalizeClass {
    public FinalizeClass(){
        System.out.println("构造函数");
    }

    protected void finalize(){
        System.out.println("终结条件");
    }

    public static void main(String[] args) {
        FinalizeClass fn=new FinalizeClass();
        System.gc();
    }
}


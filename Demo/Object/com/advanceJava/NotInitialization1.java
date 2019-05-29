package com.advanceJava;
 class SSClass{
    static{
        System.out.println("SSClass");
    }

     public SSClass(){
         System.out.println("init SSClass");
     }
}

 class SClass extends SSClass{
    static{
        System.out.println("SClass init!");
    }

    public static int value = 123;

    public SClass(){
        System.out.println("init SClass");
    }
}

 class SubClass extends SClass{
    static{
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass(){
        System.out.println("init SubClass");
    }
}

public class NotInitialization1{
    public static void main(String[] args){
        System.out.println(SubClass.value);
    }
}

package com.objectPattern;

public class SinglePattern {
    private SinglePattern(){}
    private volatile static SinglePattern instance=null;

//    public static synchronized SinglePattern getInstance(){
//        if(instance==null)
//            instance=new SinglePattern();
//        return instance;
//    }

    public static SinglePattern getInstance(){
        if(instance==null){
            synchronized (SinglePattern.class){
                if(instance==null){
                    instance=new SinglePattern();
                }
            }
        }
        return instance;
    }
}

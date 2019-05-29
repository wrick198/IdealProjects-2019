package com.objectPattern;

import java.lang.reflect.Constructor;

class StaticClassDemo{
    static {
        System.out.println("初始化静态代码块");
    }
    private static String filedstatic=staticMethod();

    private int id;
    public StaticClassDemo(Integer id){
        this.id=id;
    }
    public int getId(){
        return id;
    }


    public static String staticMethod(){
        System.out.println("初始化类变量");
        return "abc";
    }
}

public class fornameAndClassLoader {
    public static void main(String[] args) {
        try{
            Class<?> cla1= Class.forName("com.objectPattern.StaticClassDemo");
            Constructor<StaticClassDemo> constructor=StaticClassDemo.class.getConstructor(Integer.class);
            StaticClassDemo staticClassDemo=constructor.newInstance(7854);
            System.out.println(staticClassDemo.getId());
            System.out.println(cla1.getName()+","+ cla1.getPackageName());
            System.out.println("=====================");
            ClassLoader.getSystemClassLoader().loadClass("com.objectPattern.StaticClassDemo");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

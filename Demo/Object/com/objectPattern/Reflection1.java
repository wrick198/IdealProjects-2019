package com.objectPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection1 {
    public static void main(String[] args) {
        Person person=new Person();
        person.setAge(10);
        person.setName("wang");

        try{
            Class cl=Class.forName("com.objectPattern.Person");
            Field[] fields=cl.getDeclaredFields(); //属性
            for(Field fi:fields){
                fi.setAccessible(true);
                System.out.println(fi.getType()+" "+fi.getName()+" "+fi.get(person));
            }

            //通过反射获取某一个方法
            Method method=cl.getMethod("setName", String.class);
            method.invoke(person,"bei");
            Method method1=cl.getMethod("setAge", int.class);
            method1.invoke(person,20);
            System.out.println(person.getName()+" "+person.getAge());

            Method method2 = Person.class.getMethod("getName");
            System.out.println(method2.invoke(person));

            //构造方法
            Constructor<Person>[] constructors=cl.getConstructors();
            Person person1=constructors[0].newInstance();
            System.out.println(person1.getName()+" "+person1.getAge());

            Person person2=constructors[1].newInstance(30,"chang");
            System.out.println(person2.getName()+" "+person2.getAge());

            //创建一个新对象
            Person person3=(Person) cl.getConstructor().newInstance();
            System.out.println(person3.getName()+" "+person3.getAge());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

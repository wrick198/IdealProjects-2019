package com.objectPattern;

public class InnerClass {
    class Destination{
        private String label;

        Destination(String  s){
            label=s;
        }
        String readlabel(){
            return label;
        }
    }
    public void print(){
        System.out.println("123");
    }

    public Destination to(String  s){
        return new Destination(s);
    }

    public static void main(String[] args) {
        InnerClass innerClass=new InnerClass();

        InnerClass.Destination dest1=innerClass.to("ss");
        InnerClass.Destination dest2= innerClass.new Destination("dd");

        System.out.println(dest1.readlabel());
        System.out.println(dest2.readlabel());
        innerClass.print();


    }
}

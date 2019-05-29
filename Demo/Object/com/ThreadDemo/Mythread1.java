package com.ThreadDemo;

public class Mythread1 extends Thread{
    private volatile int count=6;
    private int arr=0;

    @Override
    synchronized public void run() {
        while (count>0) {
            System.out.println(this.currentThread().getName() + ": " + count);
            count--;
        }
        add(count);
    }

    public void add(int value){
        arr=arr+value;
        System.out.println("arr的值"+arr);
    }

    public int getArr(){
        return arr;
    }

    public static void main(String[] args) {
//        Mythread1 mythread1=new Mythread1();
//        Mythread1 mythread2=new Mythread1();
//        Mythread1 mythread3=new Mythread1();
//        mythread1.setName("a");
//        mythread2.setName("b");
//        mythread3.setName("c");
//        mythread1.start();
//        mythread2.start();
//        mythread3.start();

//        Mythread1 mythread4=new Mythread1();
//        Thread threada=new Thread(mythread4,"A");
//        Thread threadb=new Thread(mythread4,"B");
//        Thread threadc=new Thread(mythread4,"C");
//        Thread threadd=new Thread(mythread4,"D");
//        threada.start();
//        threadb.start();
//        threadc.start();
//        threadd.start();

        Mythread1 mythread5=new Mythread1();
        Thread threada=new Thread(mythread5,"A");
        Thread threadb=new Thread(mythread5,"B");
        threada.start();
        threadb.start();
        System.out.println("arr的值"+mythread5.getArr());
    }
}

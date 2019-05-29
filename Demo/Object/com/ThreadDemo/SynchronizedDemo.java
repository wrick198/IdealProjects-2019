package com.ThreadDemo;

class NumAdd{
    private int num=0;
    synchronized public void addI(String username){
        try{
            if(username.equals("a")){
                num=100;
                System.out.println("a set over");
                Thread.sleep(1000);
            }
            else{
                num=200;
                System.out.println("b set over");
            }
            System.out.println(username+":"+num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

class MyThread extends Thread{
    private NumAdd numAdd;
    private String username;
    MyThread(NumAdd numAdd,String username){
        super();
        this.numAdd=numAdd;
        this.username=username;
    }

    @Override
    public void run() {
        super.run();
        numAdd.addI(username);
    }
}
public class SynchronizedDemo {
    public static void main(String[] args) {
        NumAdd numAddA=new NumAdd();
        NumAdd numAddB=new NumAdd();
        MyThread myThreadA=new MyThread(numAddA,"a");
        MyThread myThreadB=new MyThread(numAddA,"b");
        myThreadA.start();
        myThreadB.start();
    }
}

package com.ThreadDemo;

public class MyTHread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            MyTHread myTHread = new MyTHread();
            myTHread.setName("mythread");
            myTHread.start();
            for (int i = 0; i < 5; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

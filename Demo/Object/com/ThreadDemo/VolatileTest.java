package com.ThreadDemo;

public class VolatileTest {
    public static  int race=0;
    private static final int THREAD_COUNT=20;
    synchronized public static void increase(){
        race++;
        System.out.println(race);
    }

    public static void main(String[] args) {
        Thread[] thread=new Thread[THREAD_COUNT];
        for (int i = 0; i <THREAD_COUNT ; i++) {
            thread[i]=new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j <10000 ; j++) {
                                increase();
                            }
                        }
                    }
            );
            thread[i].start();
        }
        System.out.println("执行结束:"+race);
    }

}

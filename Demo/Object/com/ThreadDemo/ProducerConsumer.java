package com.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Producer implements Runnable {
    List<Integer> listcache;

    Producer(List<Integer> listcache) {
        this.listcache = listcache;
    }

    private void produce() {
        synchronized (listcache) {
            try {
                while (listcache.size() == 5)
                    listcache.wait();
                Thread.sleep(1000);
                int a = new Random().nextInt();
                System.out.println("生产者生成一个产品号码:" + a);
                listcache.add(a);
                listcache.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true)
            produce();
    }
}

class Consumer implements Runnable {
    List<Integer> listcache;

    Consumer(List<Integer> listcache) {
        this.listcache = listcache;
    }

    private void consumer() {
        synchronized (listcache) {
            try {
                while (listcache.isEmpty())
                    listcache.wait();
                System.out.println("消费者消费物品号码" + listcache.remove(0));
                listcache.notifyAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true)
            consumer();
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        List<Integer> list1=new ArrayList<>();
        new Thread(new Consumer(list)).start();
        new Thread(new Consumer(list)).start();
        new Thread(new Producer(list)).start();
    }
}

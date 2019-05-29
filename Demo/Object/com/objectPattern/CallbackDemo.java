package com.objectPattern;


interface Callback {
    void printFinished(String msg);
}

class Printer {
    public void print(Callback callback, String text) {
        System.out.println("正在打印 . . . ");
        try {
            Thread.currentThread();
            Thread.sleep(3000);// 毫秒
        } catch (Exception e) {
        }
        callback.printFinished(text+"打印完成");
    }
}

class People {

    Printer printer = new Printer();

    /*
     * 回调
     */
    public void goToPrintSyn(Callback callback, String text) {
        callback.printFinished(text);
    }

    /*
     * 异步回调
     */
    public void goToPrintASyn(Callback callback, String text) {
        new Thread(new Runnable() {
            public void run() {
                printer.print(callback, text);
            }
        }).start();
    }
}



public class CallbackDemo {
    public static void main(String[] args) {
        People people=new People();
        Callback callback=new Callback() {
            @Override
            public void printFinished(String msg) {
                System.out.println("打印机输出信息："+msg);
            }
        };
        people.goToPrintSyn(callback,"打印书籍");
    }

}

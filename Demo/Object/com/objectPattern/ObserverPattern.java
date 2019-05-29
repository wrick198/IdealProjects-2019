package com.objectPattern;

import java.util.ArrayList;
import java.util.List;


class Subject{
    private int state;
    List<Observer> observerList=new ArrayList<Observer>();


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer o){
        observerList.add(o);
    }

    public void notifyAllObserver(){
        for(Observer ob:observerList)
            ob.update();
    }
}

abstract class Observer{
     protected  Subject sub;
     public abstract void update();
}

class BinaryObserver extends Observer{
    public BinaryObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary string:"+Integer.toBinaryString(this.sub.getState()));
    }
}

class OctalObserver extends Observer{
    public OctalObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal string:"+Integer.toOctalString(this.sub.getState()));
    }
}

class HexaObserver extends Observer{
    public HexaObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexa string:"+Integer.toHexString(this.sub.getState()));
    }
}



public class ObserverPattern {
    public static void main(String[] args) {
        Subject sub=new Subject();

        new BinaryObserver(sub);
        new OctalObserver(sub);
        new HexaObserver(sub);
        sub.setState(10);
        sub.setState(100);
    }
}

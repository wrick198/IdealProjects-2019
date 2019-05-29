package com.steven.SpringDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@Component
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

@Component
class BinaryObserver extends Observer{
    @Autowired
    public BinaryObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary string:"+Integer.toBinaryString(this.sub.getState()));
    }
}

@Component
class OctalObserver extends Observer{
    @Autowired
    public OctalObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal string:"+Integer.toOctalString(this.sub.getState()));
    }
}

@Component
class HexaObserver extends Observer{
    @Autowired
    public HexaObserver(Subject sub){
        this.sub=sub;
        this.sub.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexa string:"+Integer.toHexString(this.sub.getState()));
    }
}

@ContextConfiguration(locations={"classpath:application_Message.xml"})
public class ObserverPattern {
    @Autowired
    Subject sub;

    @Autowired
    BinaryObserver binaryObserver;

    @Autowired
    HexaObserver hexaObserver;

    @Autowired
    OctalObserver octalObserver;

    public void methodIsRight(){
        sub.setState(10);
    }

    public static void main(String[] args) {
        ObserverPattern observerPattern=new ObserverPattern();
        observerPattern.methodIsRight();
    }
}


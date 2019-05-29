package com.advanceJava;


class Tap{
    String str="123";
}

class Water{
    Tap t=new Tap();
}

public class Flask extends Water{
    public static void main(String[] args) {
        Flask f=new Flask();
        f.print();
    }

    void print(){
        System.out.println(super.t.str);
    }
}

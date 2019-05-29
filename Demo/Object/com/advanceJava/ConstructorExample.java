package com.advanceJava;

class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();      //访问子类的getValue方法
        System.out.println(x);
    }

    {
        i = 2;
    }

    protected int getValue() {
        return i;
    }
}

//子类
class Bar extends Foo {
    int j = 1;

    Bar() {
        System.out.println(j);
        j = 2;
    }

    {
        j = 3;
    }

    @Override
    protected int getValue() {
        return j;
    }
}

public class ConstructorExample {
    public static void main(String... args) {
        Bar bar = new Bar();
        System.out.println(bar.getValue());
    }
}

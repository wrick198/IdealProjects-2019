package com.objectPattern;

class Foo {
    int i = 1;

    Foo() {
        System.out.println(i);
        int x = getValue();  //初始化，调用子类的getvalue()方法
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

public class ConstructDemo {
    public static void main(String[] args) {
        Bar bar = new Bar();
        System.out.println(bar.getValue());
    }
}

package com.steven.SpringDemo;

public class Tiger implements Animal {

    @Override
    public void eat() {
        System.out.println("Tiger.eat()");
    }

    @Override
    public String toString() {
        return "I'm a tiger";
    }
}

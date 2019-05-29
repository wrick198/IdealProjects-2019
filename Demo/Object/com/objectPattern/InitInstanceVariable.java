package com.objectPattern;

public class InitInstanceVariable {
    private int i=1;
    private int j=geti(i);

    private int geti(int i) {
        return i;
    }

    public InitInstanceVariable(){
        System.out.println(i);
        System.out.println(j);
    }

    {
        j+=3;
    }

    public static void main(String[] args) {
        InitInstanceVariable in1=new InitInstanceVariable();
    }

}

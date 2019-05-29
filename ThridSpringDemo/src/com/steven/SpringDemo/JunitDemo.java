package com.steven.SpringDemo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class JunitDemo {

    private String message;

    //Constructor
    //@param message to be printed
    public JunitDemo(String message){
        this.message = message;
    }

    // prints the message
    public String printMessage(){
        System.out.println(message);
        return message;
    }
}
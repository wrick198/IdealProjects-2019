package com.steven.SpringDemo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class JunitDemoTest {
    String message="Hello world!";
    private JunitDemo junitDemo=new JunitDemo(message);

    @Test
    public void testPrintMessage(){
        assertNotNull(junitDemo);
        assertEquals(message,junitDemo.printMessage());
    }
}

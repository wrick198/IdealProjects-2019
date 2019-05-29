package com.steven.SpringDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application_Message.xml"})
public class ObserverTest {
    @Autowired
    Subject subject;

    @Autowired
    BinaryObserver binaryObserver;

    @Autowired
    HexaObserver hexaObserver;

    @Autowired
    OctalObserver octalObserver;

    @Test
    public void isEmptyInstance(){
        assertNotNull(subject);
        assertNotNull(binaryObserver);
        assertNotNull(hexaObserver);
        assertNotNull(octalObserver);
    }

    @Test
    public void functionIsRight(){
        subject.setState(10);
    }
}

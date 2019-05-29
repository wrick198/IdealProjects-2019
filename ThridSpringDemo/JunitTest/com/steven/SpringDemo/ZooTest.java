package com.steven.SpringDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application_Message.xml"})
public class ZooTest {
    @Autowired
    private Zoo zoo;

    @Test
    public void test(){
        assertNotNull(zoo);
        assertNotNull(zoo.getAnimal());
        System.out.println(zoo.getAnimal());
    }
}

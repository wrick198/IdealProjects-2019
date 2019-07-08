package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseServiceTest;
import com.knowledge_network.user.service.UserService;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ** Created by gongjiangtao on 2018/4/18
 **/
public class QuizeServiceTest extends BaseServiceTest {

    @Autowired
    private QuizeService quizeService;
    @Autowired
    private UserService userService;

    // @Before
    public void setUp() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd");
        String dateString = simpleDateFormat.format(new Date());

        Quize quize = new Quize();
        quize.setQuizeName("name");
        quize.setContent("abcd");
        quize.setIsAvailable(1);
        quize.setAnswer("a");
        quize.setCreateTime(simpleDateFormat.parse(dateString));
        quize.setLastModify(simpleDateFormat.parse(dateString));
        quize.setExposeTimes(0);
        quize.setRightTimes(0);
        quize.setWrongTimes(0);
        quize.setAnalysis("123");
        quize.setUrl("123");

        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/conf/spring-beans.xml");
        SessionFactoryImpl bean = (SessionFactoryImpl) context.getBean("sessionFactory");
        Session session = bean.openSession();

        session.save(quize);
    }

    @Test
    public void getQuizeById() {
        Quize quize = quizeService.getQuizeById(1);
        Assert.assertNotNull(quize);
    }

    @Test
    public void updateQuizeName() {
        Quize quize = quizeService.getQuizeById(1);
        quize = quizeService.getQuizeById(1);
        Assert.assertEquals(quize.getQuizeName(), "mengjiejie");
    }

    //    @After
    public void tearDown() throws Exception {
    }
}
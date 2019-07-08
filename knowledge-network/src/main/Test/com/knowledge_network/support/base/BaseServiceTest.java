package com.knowledge_network.support.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pentonbin on 17-12-6.
 * <p>
 * Service测试类的基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/conf/spring-beans.xml")
@Transactional
public class BaseServiceTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    protected void print(String msg) {
        Date date = new Date(System.currentTimeMillis());
        logger.debug("[TEST][" + this.getClass().getCanonicalName() + "] : " + msg);
    }
}

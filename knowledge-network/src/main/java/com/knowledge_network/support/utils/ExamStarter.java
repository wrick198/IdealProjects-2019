package com.knowledge_network.support.utils;

import com.knowledge_network.quize.entity.Paper;
import com.knowledge_network.quize.service.AnswerSheetService;
import com.knowledge_network.quize.service.PaperService;
import com.knowledge_network.quize.vo.BeginDoPaperVO;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.service.StudentService;
import com.rabbitmq.client.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * ** Created by gongjiangtao on 2018/5/15
 **/
public class ExamStarter implements Runnable {
    private Integer examineeCount;
    private String routerKey;
    private ApplicationContext context = new AnnotationConfigApplicationContext();
    static private final Logger LOGGER = Logger.getLogger(ExamStarter.class);
    private AnswerSheetService answerSheetService = context.getBean(AnswerSheetService.class);
    private StudentService studentService = context.getBean(StudentService.class);
    private PaperService paperService = context.getBean(PaperService.class);
    private Channel channel;

    public ExamStarter(Integer examineeCount, String routerKey) {
        this.examineeCount = examineeCount;
        this.routerKey = routerKey;
    }

    @Override
    public void run() {
        String location = "/META-INF/properties/rabbitmq.properties";
        try {
            Date nowMinute = DateUtils.getCurrentMinute();
            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemResourceAsStream(location));

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(prop.getProperty("host"));
            factory.setPort(Integer.parseInt(prop.getProperty("port")));
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(prop.getProperty("exam_starter_exchange"), "topic");
            channel.queueDeclare(prop.getProperty("exam_starter_queue"), true, false, false, null);
            channel.queueBind(prop.getProperty("exam_starter_queue"),
                    prop.getProperty("exam_starter_exchange"), routerKey);

            final Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String starterInfo = new String(body, "UTF-8");

                    BeginDoPaperVO beginDoPaperVO = JsonMapper.json2Obj(starterInfo, BeginDoPaperVO.class);
                    Student student = studentService.getStudentById(beginDoPaperVO.getStudentId());
                    Paper paper = paperService.getPaperById(beginDoPaperVO.getPaperId());
                    if (student != null && paper != null) {
                        answerSheetService.createEmptyAnswerSheet(student, paper, nowMinute);
                    }
                }
            };
        } catch (ParseException e) {
            LOGGER.info("Get current minute fail!");
        } catch (IOException e) {
            LOGGER.info("Fail to load property file: '" + location + "'");
        } catch (TimeoutException e) {
            LOGGER.info("Connect to rabbitmq server time out!");
        }
    }

    public String getRouterKey() {
        return routerKey;
    }

    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }
}

package com.knowledge_network.support.utils;

import com.knowledge_network.quize.entity.AnswerSheet;
import com.knowledge_network.quize.entity.StudentAnswer;
import com.knowledge_network.quize.service.AnswerSheetService;
import com.knowledge_network.quize.service.StudentAnswerService;
import com.knowledge_network.quize.vo.AnswerSheetInfoVO;
import com.knowledge_network.quize.vo.StudentAnswerInfoVO;
import com.rabbitmq.client.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * ** Created by gongjiangtao on 2018/5/15
 **/
public class AnswerSheetReceiver implements Runnable {
    private ApplicationContext context = new AnnotationConfigApplicationContext();
    private String routerKey;
    static private final Logger LOGGER = Logger.getLogger(AnswerSheetReceiver.class);
    private AnswerSheetService answerSheetService = context.getBean(AnswerSheetService.class);
    private StudentAnswerService studentAnswerService = context.getBean(StudentAnswerService.class);

    public AnswerSheetReceiver() {

    }

    public AnswerSheetReceiver(String routerKey) {
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
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(prop.getProperty("answersheet_exchange_name"), "topic");
            channel.queueDeclare(prop.getProperty("answersheet_queue_name"), true, false, false, null);
            channel.queueBind(prop.getProperty("answersheet_queue_name"), prop.getProperty("exchange_name"), routerKey);

            final Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String answersheetVOJson = new String(body, "UTF-8");


                    AnswerSheetInfoVO answerSheetInfoVO = JsonMapper.json2Obj(answersheetVOJson, AnswerSheetInfoVO.class);
                    AnswerSheet answerSheet = answerSheetService.getAnswerSheetById(answerSheetInfoVO.getId());
                    if (answerSheet != null) {
                        List<StudentAnswer> answers = new ArrayList<>();
                        for (StudentAnswerInfoVO vo : answerSheetInfoVO.getAnswers()) {
                            answers.add(studentAnswerService.createStudentAnswerByInfoVO(vo, answerSheet));
                        }
                        answerSheet.setStudentAnswers(answers);
                        answerSheet.setEndTime(nowMinute);
                        answerSheetService.saveOrUpdate(answerSheet);
                    }
                }
            };
        } catch (IOException e) {
            LOGGER.info("Can't Read properties: '" + location + "'!");
        } catch (TimeoutException e) {
            LOGGER.info("Connect to rabbitmq server time out!");
        } catch (ParseException e) {
            LOGGER.info("Get current minute error!");
        }
    }

    public String getRouterKey() {
        return routerKey;
    }

    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }
}

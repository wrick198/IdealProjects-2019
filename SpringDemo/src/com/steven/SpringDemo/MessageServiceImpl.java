package com.steven.SpringDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

interface MessageService{
    public String getMessage();
}

public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "Hello world spring!";
    }

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("application_Message.xml");
        System.out.println("context 启动");
        MessageService messageService=context.getBean(MessageService.class);
        System.out.println(messageService.getMessage());
    }
}
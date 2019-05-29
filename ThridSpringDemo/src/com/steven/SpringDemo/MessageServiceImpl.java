package com.steven.SpringDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

interface MessageService{
    String getMessage();
}

public class MessageServiceImpl implements MessageService {
    private String str1;
    private String str2;
    MessageServiceImpl(String str1){
        this.str1=str1;
    }

    @Override
    public String getMessage() {
        return "Hello world spring!";
    }

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("application_Message.xml");
        MessageService ms= context.getBean(MessageServiceImpl.class);
        System.out.println(MessageService.class);
        System.out.println(MessageServiceImpl.class);
        System.out.println(ms.getMessage());
        System.out.println(((MessageServiceImpl) ms).getStr1());
        System.out.println(((MessageServiceImpl) ms).getStr2());

        ((ClassPathXmlApplicationContext) context).close();
    }

    public String getStr1() {
        return str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
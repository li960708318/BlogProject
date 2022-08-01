package com.mszlu.blog.service.impl;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceLmpl {
    @RabbitListener(queues = {"sms.fanou.queue"})
    @RabbitHandler
    public void add(String message){
        System.out.println("接收到了"+message);
    }
}

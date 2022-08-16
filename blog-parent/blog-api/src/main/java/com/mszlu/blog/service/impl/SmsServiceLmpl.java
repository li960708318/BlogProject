package com.mszlu.blog.service.impl;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceLmpl {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @RabbitListener(queues = {"sms.fanou.queue"})
    @RabbitHandler
    public void add(String messag){
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(messag);
        //邮件标题
        message.setSubject("日常");
        //邮件内容
        message.setText("您在博客登陆了");
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }

    }
}

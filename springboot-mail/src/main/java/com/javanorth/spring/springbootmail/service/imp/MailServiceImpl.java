package com.javanorth.spring.springbootmail.service.imp;

import com.javanorth.spring.springbootmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;


    public void send() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1");
        simpleMailMessage.setTo("xx");
        simpleMailMessage.setSubject("sss");
        simpleMailMessage.setText("sss");

        mailSender.send(simpleMailMessage);
    }
}

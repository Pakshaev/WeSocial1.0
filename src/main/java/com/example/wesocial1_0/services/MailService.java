package com.example.wesocial1_0.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailService {
    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env) {
        this.env = env;
    }

    public void sendMail(String address, String title, String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(address);
        message.setSubject(title);
        message.setText(messageText);

        javaMailSender.send(message);
    }
}
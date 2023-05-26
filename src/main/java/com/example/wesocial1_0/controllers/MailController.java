package com.example.wesocial1_0.controllers;

import com.example.wesocial1_0.domain.MailMessage;
import com.example.wesocial1_0.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/mail/send")
    public void createCar(@RequestBody MailMessage message) {
        mailService.sendMail(message.getAddress(), message.getTitle(), message.getMessageText());
    }
}

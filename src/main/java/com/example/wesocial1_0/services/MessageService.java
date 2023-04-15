package com.example.wesocial1_0.services;

import com.example.wesocial1_0.domain.Message;
import com.example.wesocial1_0.domain.User;
import com.example.wesocial1_0.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void delete(Message message) {

    }

    public void update(Message messageFromDb, Message message) {

    }

    public void create(Message message, User user) {

    }
}

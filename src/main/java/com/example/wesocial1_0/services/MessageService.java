package com.example.wesocial1_0.services;

import com.example.wesocial1_0.domain.Message;
import com.example.wesocial1_0.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message getReferenceById(long id) {
        return messageRepository.getReferenceById(id);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }
}

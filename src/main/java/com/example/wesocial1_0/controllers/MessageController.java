package com.example.wesocial1_0.controllers;

import com.example.wesocial1_0.domain.Message;
import com.example.wesocial1_0.repositories.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Message getOne(@PathVariable("id") long id) {
        return messageRepository.getReferenceById(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setSentAt(new Date());
        LocalDateTime now = LocalDateTime.now();
        return messageRepository.save(message);
    }

    @PutMapping("/{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }

}

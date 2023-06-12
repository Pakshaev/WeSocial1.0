package com.example.wesocial1_0.controllers;

import com.example.wesocial1_0.domain.Message;
import com.example.wesocial1_0.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Message getOne(@PathVariable("id") Long id) {
        return messageService.getReferenceById(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreated(new Date());
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        message.setUpdated(new Date());
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageService.save(messageFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }

}

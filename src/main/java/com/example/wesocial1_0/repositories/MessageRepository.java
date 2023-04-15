package com.example.wesocial1_0.repositories;

import com.example.wesocial1_0.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

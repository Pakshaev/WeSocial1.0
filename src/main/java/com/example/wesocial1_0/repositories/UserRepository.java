package com.example.wesocial1_0.repositories;

import com.example.wesocial1_0.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByUsername(String username);

    User findByMessagesId(Long id);
}
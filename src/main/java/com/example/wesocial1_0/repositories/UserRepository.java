package com.example.wesocial1_0.repositories;

import com.example.wesocial1_0.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByUsername(String username);
}
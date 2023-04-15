package com.example.wesocial1_0.repositories;

import com.example.wesocial1_0.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    /*User findByActivationCode(String code);*/
}

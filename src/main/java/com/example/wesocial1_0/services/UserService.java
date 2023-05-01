package com.example.wesocial1_0.services;

import com.example.wesocial1_0.domain.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}

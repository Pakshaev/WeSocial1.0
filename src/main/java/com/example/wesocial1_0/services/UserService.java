package com.example.wesocial1_0.services;

import com.example.wesocial1_0.domain.user.User;
import com.example.wesocial1_0.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    public List<User> findByUsername(String username) {
        List<User> result = userRepository.findByUsername(username);
        log.info("IN findByUsername - users: {} found by username: {}", result.size(), username);
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if(result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    public boolean delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
        return true;
    }
/*
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id); // Предполагается наличие UserRepository для доступа к данным

        if (existingUser != null) {
            // Обновление нечувствительных к безопасности полей пользователя
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            // Обновление чувствительных к безопасности полей пользователя
            // Например, проверка и обновление пароля
            if (updatedUser.getPassword() != null) {
                String hashedPassword = hashPassword(updatedUser.getPassword()); // Хэширование нового пароля
                existingUser.setPassword(hashedPassword);
            }

            // Сохранение обновленного пользователя в репозитории
            User savedUser = userRepository.save(existingUser);
            log.info("IN update - user with id: {} successfully updated", id);
            return savedUser;
        } else {
            return null; // Возвращаем null, если пользователь с указанным идентификатором не найден
        }
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));
    }

    public User findByMessageId(Long messageId) {
        User user = userRepository.findByMessagesId(messageId);
        log.info("IN findByMessagesId - user: {} found by messageId: {}", user, messageId);
        return user;
    }

}

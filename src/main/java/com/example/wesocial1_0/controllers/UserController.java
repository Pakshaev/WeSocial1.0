package com.example.wesocial1_0.controllers;

import com.example.wesocial1_0.domain.dto.UserDTO;
import com.example.wesocial1_0.domain.user.User;
import com.example.wesocial1_0.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{messageId}/user")
    public ResponseEntity<User> getUserByMessageId(@PathVariable("messageId") Long messageId) {
        User user = userService.findByMessageId(messageId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserDTO>> getUserByUsername(@PathVariable("username") String username) {
        List<User> users = userService.findByUsername(username);
        List<UserDTO> newList = new ArrayList<>();
        for (User user : users) {
            newList.add(new UserDTO(user));
        }
        if (!users.isEmpty()) {
            return new ResponseEntity<>(newList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAll();
        List<UserDTO> newList = new ArrayList<>();
        for (User user : users) {
            newList.add(new UserDTO(user));
        }
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<UserDTO> setStatus(@PathVariable("id") Long id, @RequestBody String status) {
        User user = userService.findById(id);
        user.setStatus(status);
        userService.save(user);
        UserDTO userDTO = new UserDTO(user);
        /*if (user != null) {

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PutMapping("/locale/{id}")
    public ResponseEntity<UserDTO> setLocale(@PathVariable("id") Long id, @RequestBody String locale) {
        User user = userService.findById(id);
        if (user != null) {
            user.setLocale(locale);
            userService.save(user);
            UserDTO userDTO = new UserDTO(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(id, user);
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

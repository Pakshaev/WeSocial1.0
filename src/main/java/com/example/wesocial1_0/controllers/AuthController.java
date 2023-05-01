package com.example.wesocial1_0.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @PostMapping("/login")
    public CurrentUser login() {
        return null;
    }

    @PostMapping("/logout")
    public LogoutResponse logout() {
        return new LogoutResponse();
    }

    @GetMapping("/current-user")
    public CurrentUser getCurrentUser() {
        return new CurrentUser(1, "Jerry");
    }

    public record CurrentUser(Integer id, String nickname) {}
    public record LogoutResponse() {}
}
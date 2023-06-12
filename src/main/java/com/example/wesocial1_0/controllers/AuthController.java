package com.example.wesocial1_0.controllers;

import com.example.wesocial1_0.domain.rest.AuthenticationRequest;
import com.example.wesocial1_0.domain.rest.AuthenticationResponse;
import com.example.wesocial1_0.domain.rest.RegisterRequest;
import com.example.wesocial1_0.domain.user.User;
import com.example.wesocial1_0.services.AuthService;
import com.example.wesocial1_0.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final UserService userService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        User user = userService.findByEmail(request.getEmail());
        user.setLastVisit(new Date());
        return service.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
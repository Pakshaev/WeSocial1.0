package com.example.wesocial1_0.services;

import com.example.wesocial1_0.domain.dto.UserDTO;
import com.example.wesocial1_0.domain.rest.AuthenticationResponse;
import com.example.wesocial1_0.domain.rest.AuthenticationRequest;
import com.example.wesocial1_0.domain.rest.RegisterRequest;
import com.example.wesocial1_0.domain.user.User;
import com.example.wesocial1_0.repositories.UserRepository;
import com.example.wesocial1_0.configuration.security.JwtService;
import com.example.wesocial1_0.token.Token;
import com.example.wesocial1_0.token.TokenRepository;
import com.example.wesocial1_0.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import static com.example.wesocial1_0.domain.user.Role.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostConstruct
    private void regAdmin() {
        if (repository.findByEmail("admin@gmail.com")!=null) {
            return;
        }
        RegisterRequest admin = RegisterRequest.builder()
                .name("admin")
                .email("admin@gmail.com")
                .password("admin")
                .role(ADMIN)
                .build();
        register(admin);

        // test role1
        RegisterRequest role1 = RegisterRequest.builder()
                .name("user")
                .email("user@gmail.com")
                .password("user")
                .role(USER)
                .build();
        register(role1);

        // test role2
        RegisterRequest role2 = RegisterRequest.builder()
                .name("role2")
                .email("empty@gmail.com")
                .password("empty")
                .role(EMPTY)
                .build();
        register(role2);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        User savedUser = repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        UserDTO userDTO = new UserDTO(repository.findByEmail(user.getEmail()));
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(userDTO.getId())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = this.repository.findByEmail(userEmail);
            if (!jwtService.isTokenValid(refreshToken, user)) {
                throw new ResponseStatusException(FORBIDDEN, "Invalid token");
            }
            String accessToken = jwtService.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            AuthenticationResponse authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        }
    }
}

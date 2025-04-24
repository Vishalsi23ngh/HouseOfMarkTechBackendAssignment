package com.example.houseOfMarkTechBackend.controller;

import com.example.houseOfMarkTechBackend.model.AuthRequest;
import com.example.houseOfMarkTechBackend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService = new AuthService();

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        String token = authService.register(request.getUsername(), request.getPassword());
        if (token == null) return "User already exists";
        return token;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        if (token == null) return "Invalid credentials";
        return token;
    }
}

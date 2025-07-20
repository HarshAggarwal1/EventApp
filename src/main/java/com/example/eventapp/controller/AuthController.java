package com.example.eventapp.controller;
import com.example.eventapp.dto.AuthRequest;
import com.example.eventapp.dto.AuthResponse;
import com.example.eventapp.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register") public AuthResponse register(@RequestBody AuthRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login") public AuthResponse login(@RequestBody AuthRequest req) {
        return authService.login(req);
    }
}

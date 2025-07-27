package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.auth.request.LoginRequest;
import com.juannn.Login_register.dto.auth.request.RegisterRequest;
import com.juannn.Login_register.dto.auth.response.TokenResponse;
import com.juannn.Login_register.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    //Dependencies
    private final AuthService service;

    //Register user
    @PostMapping("/register")
    public ResponseEntity<TokenResponse> registerUser(@RequestBody final RegisterRequest request) {
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }

    //Login user
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticateUser(@RequestBody final LoginRequest request) {
        final TokenResponse token = service.login(request);
        return ResponseEntity.ok(token);
    }

    //Refresh token
    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return service.refreshToken(authHeader);
    }
}

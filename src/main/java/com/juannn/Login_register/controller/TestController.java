package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.auth.request.RegisterRequest;
import com.juannn.Login_register.dto.auth.response.TokenResponse;
import com.juannn.Login_register.dto.university.request.UniversityRequest;
import com.juannn.Login_register.model.University;
import com.juannn.Login_register.service.AuthService;
import com.juannn.Login_register.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AuthService AuthService;
    private final TestService testService;

    //Register user
    @PostMapping("/registerAdmin")
    public ResponseEntity<TokenResponse> registerAdmin(@RequestBody final RegisterRequest request) {
        final TokenResponse token = AuthService.RegisterAdmin(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registerUniversity")
    public ResponseEntity<University> registerUniversity(@RequestBody final UniversityRequest requestDTO) {
        return testService.registUniversity(requestDTO);
    }
}

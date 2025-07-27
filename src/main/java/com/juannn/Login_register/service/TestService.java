package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.university.request.UniversityRequest;
import com.juannn.Login_register.mapper.UserMapper;
import com.juannn.Login_register.model.University;
import com.juannn.Login_register.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;



    //Only for testing purposes

    //Regist new University
    public University registUniversity(UniversityRequest requestDTO) {
    }

    //Regist new Campus



    //Regist new locality
}

package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.auth.request.LoginRequest;
import com.juannn.Login_register.dto.auth.request.RegisterRequest;
import com.juannn.Login_register.dto.auth.response.TokenResponse;
import com.juannn.Login_register.mapper.UserMapper;
import com.juannn.Login_register.model.user.Role;
import com.juannn.Login_register.model.user.Token;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.TokenRepository;
import com.juannn.Login_register.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService{

    // Dependencies
    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(RegisterRequest request) {
        var user = userMapper.toDomain(request); // Map the request to a domain object
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password before saving
        user.setRoles(Set.of(Role.CLIENT));

        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(savedUser); // Generate JWT token for the user
        var refreshToken = jwtService.generateRefreshToken(savedUser); // Generate refresh token for the user

        saveUserToken(savedUser, jwtToken); // Save JWT refresh token to the database
        return new TokenResponse(jwtToken, refreshToken);
    }

    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return new TokenResponse(jwtToken, refreshToken);
    }

    public TokenResponse refreshToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail).orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                return new TokenResponse(accessToken, newRefreshToken);
            }
        }
        throw new IllegalArgumentException("Invalid refresh token");
    }

    private void saveUserToken(User user, String refreshToken) {
        //Create a new token object and save it to the database
        var token = Token.builder()
                .user(user)
                .token(refreshToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        final List<Token> validUserTokens = tokenRepository
                .findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
        if(!validUserTokens.isEmpty()) {
            for (final Token token : validUserTokens) {
                token.setExpired(true);
                token.setRevoked(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }
    }

    // Only for testing purposes

    // Regist new admin
    public TokenResponse RegisterAdmin (RegisterRequest request){
        var user = userMapper.toDomain(request); // Map the request to a domain object
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password before saving
        user.setRoles(Set.of(Role.SUPER_ADMIN));

        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(savedUser); // Generate JWT token for the user
        var refreshToken = jwtService.generateRefreshToken(savedUser); // Generate refresh token for the user

        saveUserToken(savedUser, jwtToken); // Save JWT refresh token to the database
        return new TokenResponse(jwtToken, refreshToken);
    }

}

package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(UUID id);
    Optional<Token> findByToken(String jwtToken);
}

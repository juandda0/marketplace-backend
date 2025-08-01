package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.user.StudentWhitelist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentWhitelistRepository extends JpaRepository<StudentWhitelist, String> {
}

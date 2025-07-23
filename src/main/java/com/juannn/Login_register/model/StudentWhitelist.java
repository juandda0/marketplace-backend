package com.juannn.Login_register.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student_whitelist")
@Data
@NoArgsConstructor
public class StudentWhitelist {

    @Id
    @Column(name = "student_id")
    private String studentId;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String fullName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    @Column(nullable = false)
    private boolean isClaimed = false;
}
package com.juannn.Login_register.model.user;

import com.juannn.Login_register.model.uni.Campus;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity StudentWhitelist: Represents a student authorized to register.
 * <p>
 * A student whitelist is a student who is authorized to register for an account on the platform.
 * The entity contains the student's ID, full name, and campus, as well as a flag indicating whether the student has claimed their account.
 * If the student has claimed their account, the entity also contains the ID of the user who claimed the account and the timestamp when the account was claimed.
 */
@Entity
@Table(name = "student_whitelist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentWhitelist {

    /**
     * The student's ID.
     */
    @Id
    @Column(name = "student_id")
    private String studentId;

    /**
     * The student's full name.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String fullName;

    /**
     * The campus where the student is enrolled.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    /**
     * Whether the student has claimed their account.
     */
    @Column(nullable = false)
    private boolean isClaimed = false;

    /**
     * The ID of the user who claimed the account.
     */
    @Column(name = "claimed_by_user_id")
    private UUID claimedByUserId;

    /**
     * The timestamp when the account was claimed.
     */
    @Column(name = "claimed_at")
    private LocalDateTime claimedAt;
}
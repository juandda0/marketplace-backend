package com.juannn.Login_register.model.uni;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entity Campus: Represents a campus for a university.
 * A campus is a physical location where students can pick up their orders.
 */
@Entity
@Table(name = "campuses")
@Data
@NoArgsConstructor
public class Campus {

    /**
     * Unique identifier for the campus.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the campus.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    /**
     * University that the campus belongs to.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;
}
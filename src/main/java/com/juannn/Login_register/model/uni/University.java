package com.juannn.Login_register.model.uni;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Entity University: Represents a university in the platform.
 * A university is an institution of higher learning that is associated with one or more campuses.
 * The university contains the name, logo, and terms of service URL.
 */
@Entity
@Table(name = "universities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class University {

    /**
     * Unique identifier of the university.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the university.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * URL of the university logo.
     */
    @Column(columnDefinition = "TEXT")
    private String logoUrl;

    /**
     * Subdomain of the university.
     */
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String subdomain;

    /**
     * Whether the university is active or not.
     */
    @NotNull
    @Column(nullable = false)
    private boolean isActive = true;

    /**
     * URL of the university terms of service.
     */
    @Column(columnDefinition = "TEXT")
    private String termsUrl;
}
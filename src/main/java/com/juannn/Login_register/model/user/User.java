package com.juannn.Login_register.model.user;

import com.juannn.Login_register.model.uni.Campus;
import com.juannn.Login_register.model.uni.University;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    // --- Basic information ---
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    //---Roles---
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Set<Role> roles;

    //---Specific for UNIVERSITY_ADMIN---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managed_university_id")
    private University managedUniversity;

    //---Profile and states---
    @Column(name = "profile_photo_url", columnDefinition = "TEXT")
    private String profilePhotoUrl;

    @Column(length = 20)
    private String phone;

    @Size(max = 500)
    @Column(length = 500)
    private String bio;

    //---Satete---
    @Builder.Default
    private boolean isActive = true;

    @Builder.Default
    private boolean isVerified = false;

    @CreationTimestamp //manage by Hibernate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive; // Account is blocked if is not active
    }

    @Override
    public boolean isEnabled() {
        return isVerified; // User is enable only if is verified
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public University getManagedUniversity() {
        return managedUniversity;
    }

    public void setManagedUniversity(University managedUniversity) {
        this.managedUniversity = managedUniversity;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public @NotNull Campus getCampus() {
        return campus;
    }

    public void setCampus(@NotNull Campus campus) {
        this.campus = campus;
    }

    public @NotBlank @Size(max = 50) String getStudentId() {
        return studentId;
    }

    public void setStudentId(@NotBlank @Size(max = 50) String studentId) {
        this.studentId = studentId;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 100) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank @Size(max = 100) String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank @Size(max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 100) String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
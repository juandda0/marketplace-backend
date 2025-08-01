package com.juannn.Login_register.model.store;

import com.juannn.Login_register.model.uni.Campus;
import com.juannn.Login_register.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity Store: Represents an online store in the platform.
 * A store is associated with one user (seller) and one campus.
 * The store contains the name, description, logo and banner of the store.
 * It also contains the average rating, rating count, and total sales of the store.
 */
@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    /**
     * The id of the store.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user that owns the store.
     */
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false, unique = true)
    private User seller;

    /**
     * The campus where the store is located.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    /**
     * The name of the store.
     */
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The description of the store.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The URL of the logo of the store.
     */
    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    /**
     * The URL of the banner of the store.
     */
    @Column(name = "banner_url", columnDefinition = "TEXT")
    private String bannerUrl;

    /**
     * The average rating of the store.
     */
    @Builder.Default
    private double averageRating = 0.0;

    /**
     * The number of ratings of the store.
     */
    @Builder.Default
    private int ratingCount = 0;

    /**
     * The total sales of the store.
     */
    @Builder.Default
    private int totalSales = 0;

    /**
     * The status of the store. Must be set in the service.
     */
    private StoreStatus status;

    /**
     * The date when the store was created.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
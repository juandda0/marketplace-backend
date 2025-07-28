package com.juannn.Login_register.model.order;

import com.juannn.Login_register.model.store.Store;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity OrderPackage: Represents an order package, grouped by seller.
 * Allows each seller to manage their part of the order independently.
 */

@Entity
@Table(name = "order_packages")
@Data
@NoArgsConstructor
public class OrderPackage {

    /**
     * Unique identifier of the order package.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The order that this package belongs to.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * The store that is responsible for this package.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /**
     * Current status of the package (awaiting_shipment, shipped, delivered, cancelled).
     */
    @Size(max = 50)
    @Column(length = 50)
    private PackageStatus status; // Must be set in the service

    /**
     * Timestamp of the last update to this package.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
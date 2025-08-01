package com.juannn.Login_register.model.uni;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entity DeliveryPoint: Represents a delivery point for a campus.
 * A delivery point is a physical location within a campus where students can pick up their orders.
 * The delivery point is associated with one campus, and contains multiple orders.
 */
@Entity
@Table(name = "delivery_points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPoint {

    /**
     * Unique identifier for the delivery point.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the delivery point.
     */
    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String name;

    /**
     * Description of the delivery point.
     */
    @NotBlank
    @Size(max = 500)
    private String description;

    /**
     * The campus that the delivery point belongs to.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    /**
     * Whether the delivery point is active or not.
     * This field allows the admin to deactivate a delivery point without deleting it,
     * for example, for remodeling.
     */
    @Column(nullable = false)
    private boolean isActive = true;
}
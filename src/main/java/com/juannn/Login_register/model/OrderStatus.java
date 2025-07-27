package com.juannn.Login_register.model;

public enum OrderStatus {
    PENDING_PAYMENT, // Waiting for payment confirmation.
    PROCESSING,      // Payment received, sellers are preparing the packages.
    SHIPPED,         // All packages have been shipped/delivered.
    COMPLETED,       // The order was successfully completed (after delivery).
    CANCELLED,       // The order was cancelled.
    REFUNDED         // A refund has been issued.
}

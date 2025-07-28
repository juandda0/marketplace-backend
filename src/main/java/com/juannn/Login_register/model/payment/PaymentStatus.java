package com.juannn.Login_register.model.payment;

public enum PaymentStatus {
    PENDING, // Waiting for payment confirmation.
    SUCCESSFUL, // Payment received, sellers are preparing the packages.
    FAILED, // Payment failed.
    REFUNDED // A refund has been issued.
}

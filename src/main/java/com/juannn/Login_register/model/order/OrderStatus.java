/**
 * Enum representing the status of an order in the university marketplace.
 * This status helps control the steps that the order goes through from creation to completion.
 */
package com.juannn.Login_register.model.order;

public enum OrderStatus {
    /**
     * Waiting for payment confirmation.
     */
    PENDING_PAYMENT,

    /**
     * Payment received, sellers are preparing the packages.
     */
    PROCESSING,

    /**
     * All packages have been shipped/delivered.
     */
    SHIPPED,

    /**
     * The order was successfully completed (after delivery).
     */
    COMPLETED,

    /**
     * The order was cancelled.
     */
    CANCELLED,

    /**
     * A refund has been issued.
     */
    REFUNDED
}
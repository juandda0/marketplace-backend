/**
 * Enum PackageStatus: Represents the status of an order package.
 * An order package is a group of items in an order, which can be shipped
 * or delivered independently. The status helps control the flow of the
 * order process.
 */
package com.juannn.Login_register.model.order;

public enum PackageStatus {
    /**
     * AWAITING_SHIPMENT: The seller has been notified and is preparing the package.
     */
    AWAITING_SHIPMENT,

    /**
     * SHIPPED: The seller has shipped or delivered the package.
     */
    SHIPPED,

    /**
     * DELIVERED: The buyer has confirmed receipt of the package.
     */
    DELIVERED,

    /**
     * CANCELLED: The seller cancelled this package.
     */
    CANCELLED
}
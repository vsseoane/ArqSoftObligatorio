
package com.roi.supplying.orders;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() { 
        super("Order not found.");
    }
}
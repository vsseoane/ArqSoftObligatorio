
package com.roi.supplying.order;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() { 
        super("Order not found.");
    }
}
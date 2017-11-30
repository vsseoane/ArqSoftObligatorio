
package com.roi.planner.plan;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() { 
        super("Order not found.");
    }
}
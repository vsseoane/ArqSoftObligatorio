
package com.roi.planner.plan;

public class OrderNotValidException extends Exception {
    public OrderNotValidException() { 
        super("Order not valid.");
    }
}
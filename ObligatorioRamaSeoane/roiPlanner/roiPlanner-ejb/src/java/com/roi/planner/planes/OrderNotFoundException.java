
package com.roi.planner.planes;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException() { 
        super("Order not found.");
    }
}
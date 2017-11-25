
package com.roi.planner.planes;

public class OrderNotValidException extends Exception{
    public OrderNotValidException() { 
        super("Order not valid.");
    }
}
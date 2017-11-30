
package com.roi.supplying.order;

public class OrderNotValidException extends Exception {
    public OrderNotValidException() { 
        super("Order not valid.");
    }
}
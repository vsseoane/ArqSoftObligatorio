
package com.supplying.orders;

public class OrderNotValidException extends Exception{
    public OrderNotValidException() { 
        super("Order not valid.");
    }
}
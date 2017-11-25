package com.supplying.orders;

import java.io.Serializable;

public class RequestDTO implements Serializable  {
    Order order;
    String message;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

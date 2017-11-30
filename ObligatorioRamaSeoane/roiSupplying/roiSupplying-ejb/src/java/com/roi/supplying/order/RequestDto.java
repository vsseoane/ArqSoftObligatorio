package com.roi.supplying.order;

import java.io.Serializable;

public class RequestDto implements Serializable  {
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

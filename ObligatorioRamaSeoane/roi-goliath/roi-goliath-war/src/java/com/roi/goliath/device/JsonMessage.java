package com.roi.goliath.device;

public class JsonMessage {

    private String message;

    public JsonMessage() {
    }

    public JsonMessage(String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }
}

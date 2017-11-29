package com.roi.goliath.device;

import java.io.Serializable;

/**
 *
 * @author Alejandro
 */
public class CommandValue implements Serializable {

    private String command;

    private String value;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

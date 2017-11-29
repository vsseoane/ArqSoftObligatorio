package com.roi.goliath.device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeviceScheduleDto implements Serializable {

    private String actuatorId;

    private List<CommandValue> commands;

    public DeviceScheduleDto() {
        this.commands = new ArrayList<>();
    }

    public String getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(String actuatorId) {
        this.actuatorId = actuatorId;
    }

    public List<CommandValue> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandValue> commands) {
        this.commands = commands;
    }

    public List<String> getCommandsToExecute() {
        List<String> commandsToExecute = new ArrayList<>();
        for (int i = 0; i < this.commands.size(); i++) {
            CommandValue cmd = this.commands.get(i);
            commandsToExecute.add(cmd.getCommand() + "(" + cmd.getValue() + ")");
        }
        return commandsToExecute;
    }

}

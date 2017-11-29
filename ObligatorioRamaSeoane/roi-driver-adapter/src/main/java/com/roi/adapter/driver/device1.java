package com.roi.adapter.driver;

import java.util.Map;

public class device1 {

    public void execute(Map<String, String> commandMap) {
        commandMap.entrySet().forEach(e -> executeCommand(e.getKey(), e.getValue()));
    }

    private void executeCommand(String command, String argument) {
        switch (command) {
            case "TURN_ON":
                TURN_ON();
                break;
            case "TURN_OFF":
                TURN_OFF();
                break;
            case "ENABLE":
                ENABLE(argument);
                break;
            case "DISABLE":
                DISABLE(argument);
                break;
            case "SET_LEVEL":
                SET_LEVEL(argument);
                break;
            case "SET_TIMER":
                SET_TIMER(argument);
                break;
        }
    }

    private void invoked(String name, String argument) {
        System.out.printf("[%s]: method [%s] invoked with argument [%s]!\n",
                getClass().getName(), name, argument);
    }

    public void TURN_ON() {
        invoked("TURN_ON", "<None>");
    }

    public void TURN_OFF() {
        invoked("TURN_OFF", "<None>");
    }

    public void ENABLE(String date) {
        invoked("ENABLE", date);
    }

    public void DISABLE(String date) {
        invoked("DISABLE", date);
    }

    public void SET_LEVEL(String level) {
        invoked("SET_LEVEL", level);
    }

    public void SET_TIMER(String timer) {
        invoked("SET_TIMER", timer);
    }

}

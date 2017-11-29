
package com.roi.planner.programmer;

 
public class ActuatorNotFoundException extends Exception {
    public ActuatorNotFoundException() { 
        super("Actuator not found.");
    }
}
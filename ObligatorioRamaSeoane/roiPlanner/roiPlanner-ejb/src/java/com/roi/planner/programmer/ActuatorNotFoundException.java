
package com.roi.planner.programmer;

import com.roi.planner.planes.*;
 
public class ActuatorNotFoundException extends Exception{
    public ActuatorNotFoundException() { 
        super("Actuator not found.");
    }
}
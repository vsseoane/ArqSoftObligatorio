
package com.roi.planner.planes;

public class PlanNotFoundException extends Exception {
    public PlanNotFoundException() { 
        super("Plan not found.");
    }
}
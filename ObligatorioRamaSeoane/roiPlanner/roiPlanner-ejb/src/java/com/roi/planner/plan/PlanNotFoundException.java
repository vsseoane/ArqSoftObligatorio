
package com.roi.planner.plan;

public class PlanNotFoundException extends Exception {
    public PlanNotFoundException() { 
        super("Plan not found.");
    }
}
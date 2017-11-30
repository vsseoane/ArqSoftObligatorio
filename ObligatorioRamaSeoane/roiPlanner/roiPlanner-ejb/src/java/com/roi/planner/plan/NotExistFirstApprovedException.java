
package com.roi.planner.plan;

public class NotExistFirstApprovedException extends Exception {
    public NotExistFirstApprovedException() { 
        super("First approve should be approved.");
    }
}

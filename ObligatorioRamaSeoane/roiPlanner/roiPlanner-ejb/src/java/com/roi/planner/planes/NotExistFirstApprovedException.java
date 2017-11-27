
package com.roi.planner.planes;

public class NotExistFirstApprovedException extends Exception{
    public NotExistFirstApprovedException() { 
        super("First approve should be approved.");
    }
}

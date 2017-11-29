
package com.roi.planner.programmer;

import java.io.Serializable;
import java.util.List;


public class ActuatorProgrammingRequest implements Serializable {
    private List<ActuatorProgramming> actuatorProgamming;
    private Long idPlan;
    
    public ActuatorProgrammingRequest() {
        
    }
    
    public List<ActuatorProgramming> getActuatorProgamming() {
        return actuatorProgamming;
    }

    public void setActuatorProgamming(List<ActuatorProgramming> actuatorProgamming) {
        this.actuatorProgamming = actuatorProgamming;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }
    
}

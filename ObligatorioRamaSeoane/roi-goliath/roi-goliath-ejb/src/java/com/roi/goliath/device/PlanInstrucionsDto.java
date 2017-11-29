package com.roi.goliath.device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class PlanInstrucionsDto implements Serializable {

    private int idPlan;

    private List<DeviceScheduleDto> actuatorProgamming;

    public PlanInstrucionsDto() {
        this.actuatorProgamming = new ArrayList<>();
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public List<DeviceScheduleDto> getActuatorProgamming() {
        return actuatorProgamming;
    }

    public void setActuatorProgamming(List<DeviceScheduleDto> actuatorProgamming) {
        this.actuatorProgamming = actuatorProgamming;
    }

}

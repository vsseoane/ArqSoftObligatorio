package com.roi.goliath.device;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alejandro
 */
@Path("/device")
public class DeviceResource {

    @EJB
    private DeviceBean deviceBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeCommand(PlanInstrucionsDto planSchedule) {
        planSchedule.getActuatorProgamming().forEach((deviceScheduleDto) -> {
            deviceBean.ExecuteCommand(deviceScheduleDto);
        });
        return Response.ok(new JsonMessage("SUCCESS!")).build();
    }
}

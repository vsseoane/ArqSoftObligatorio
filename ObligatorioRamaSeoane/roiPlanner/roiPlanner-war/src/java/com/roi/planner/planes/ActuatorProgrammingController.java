package com.roi.planner.planes;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.roi.planner.programmer.ActuatorNotFoundException;
import com.roi.planner.programmer.ActuatorProgramming;
import com.roi.planner.programmer.ActuatorProgrammingBean;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

@Path("actuatorProgramming")
public class ActuatorProgrammingController {
    @EJB
    public ActuatorProgrammingBean actuatorProgrammingBean;
    private final Gson gson;

    @Context
    private UriInfo context;
    
    public ActuatorProgrammingController() {
        gson = new Gson();       
    }
    
    @PUT
    @Path("/commands")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response programming(String jsonOrder, @HeaderParam("token") 
            String token)  {
        try {
            ActuatorProgramming actuator = gson.fromJson(jsonOrder, 
                    ActuatorProgramming.class);
            this.actuatorProgrammingBean.actuatorProgramming(actuator);
            return Response.status(Response.Status.OK).build(); 
        } catch (ActuatorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe"
                    + " ese actuador").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un"
                    + " error al realizar su solicitud").build();
        } 
    }
}

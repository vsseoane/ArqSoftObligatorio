package com.roi.planner.controller;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.roi.planner.planes.PlanBean;
import com.roi.planner.planes.PlanNotFoundException;
import com.roi.planner.programmer.ActuatorNotFoundException;
import com.roi.planner.programmer.ActuatorProgramming;
import com.roi.planner.programmer.ActuatorProgrammingBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("actuatorProgramming")
public class ActuatorProgrammingController {
    private Gson gson;
    @Context
    private UriInfo context;

    public ActuatorProgrammingController() {
        gson = new Gson();       
    }
    
    @EJB
    public ActuatorProgrammingBean actuatorProgrammingBean;
      
    @PUT
    @Path("/commands")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response programming(String jsonOrder,@HeaderParam("token") String token)  {
        try {
           
            ActuatorProgramming actuator = gson.fromJson(jsonOrder, ActuatorProgramming.class);
            this.actuatorProgrammingBean.actuatorProgramming(actuator);
            return Response.status(Response.Status.OK).build(); 
        }catch(ActuatorNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe ese actuador").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        } 
    }
    
    
}

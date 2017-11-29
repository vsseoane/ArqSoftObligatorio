package com.roi.planner.controller;

import com.google.gson.Gson;
import com.roi.planner.planes.NotExistFirstApprovedException;
import com.roi.planner.planes.Plan;
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

@Path("plan")
public class PlanController {
    private Gson gson;
    @Context
    private UriInfo context;
    

    public PlanController() {
        gson = new Gson();
    }

   @EJB
    public PlanBean planBean;
    
   @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
         try {
            final List<Plan> plans = planBean.getPlans();
            final String JSONRepresentation = gson.toJson(plans);
            return Response.status(Response.Status.OK).entity(JSONRepresentation).build(); 
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        }
    }

    @PUT
    @Path("/approvePlan")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approvePlan(@HeaderParam("id") int id)  {
        try {
            planBean.approvePlan(id);
           return Response.status(Response.Status.OK).build(); 
        }catch(PlanNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe ese plan").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        } 
    }
    @PUT
    @Path("/secondApprovePlan")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response secondApprovePlan(@HeaderParam("id") int id)  {
        try {
            planBean.secondApprovePlan(id);
           return Response.status(Response.Status.OK).build(); 
        }catch(PlanNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe ese plan").build();
         }catch(NotExistFirstApprovedException e){
            return Response.status(Response.Status.NOT_FOUND).entity("Falta la primera aprobación").build();
        }catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        } 
    }
    
}

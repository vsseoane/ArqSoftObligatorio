package com.roi.planner.controllers;

import com.google.gson.Gson;
import com.roi.planner.plan.Plan;
import com.roi.planner.plan.PlanBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("plan")
public class PlanController {
    private Gson gson;

    @Context
    private UriInfo context;
    
    @EJB
    private PlanBean planBean;

            
    public PlanController() {
         gson = new Gson();
    }

    @GET
  //  @Produces(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlan() {
        return "Response";
       /* try {
            final List<Plan> plans = planBean.getPlans();
            final Gson gson = new Gson();
            final String JSONRepresentation = gson.toJson(plans);
        return Response.status(Response.Status.OK).entity(JSONRepresentation).build(); 
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        }*/
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String jsonPlan) {
        try {
           Gson gson = new Gson();
           Plan plan = gson.fromJson(jsonPlan, Plan.class);
           Plan createdAuthor = planBean.createPlan(plan);
            return Response.status(Response.Status.OK).entity(createdAuthor).build(); 
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        }     
    }
}

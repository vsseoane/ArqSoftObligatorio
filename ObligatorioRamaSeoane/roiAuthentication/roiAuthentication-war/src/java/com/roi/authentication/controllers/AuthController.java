
package com.roi.authentication.controllers;

import com.roi.authentication.auth.ApplicationBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController {

    @Context
    private UriInfo context;

    public AuthController() {
       
    }
     @EJB
    public ApplicationBean applicationBean;
     
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@HeaderParam("token1") String token1, @HeaderParam("token2") String token2) {
     String a = "";
        try {
            boolean hasPermission = applicationBean.HasPermission(token1, token2);
            return Response.status(Response.Status.OK).entity(hasPermission).build(); 
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hubo un error al realizar su solicitud").build();
        }
    }
        

}

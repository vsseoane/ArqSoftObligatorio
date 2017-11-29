
package com.roi.authentication.controllers;

import com.roi.authentication.auth.ApplicationBean;
import com.roi.authentication.auth.AuthDTO;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

@Path("auth")
public class AuthController {

    @Context
    private UriInfo context;
    private final Gson gson;
    public AuthController() {
        gson = new Gson();
    }
    
    @EJB
    public ApplicationBean applicationBean;
     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(String jsonOrder) {
        AuthDTO authDTO = gson.fromJson(jsonOrder, AuthDTO.class);
        boolean hasPermission = false;
        try {
          hasPermission = applicationBean.HasPermission(authDTO.getToken1(), authDTO.getToken2());
           
        } catch (Exception e) {
           return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("")
                    .build();
        }
        return Response
                    .status(Response.Status.OK)
                    .entity("hasPermission")
                    .build();
    }
        

}

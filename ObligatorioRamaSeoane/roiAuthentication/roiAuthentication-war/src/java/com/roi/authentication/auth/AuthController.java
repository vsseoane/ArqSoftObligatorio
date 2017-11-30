package com.roi.authentication.auth;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

@Path("auth")
public class AuthController {
    @EJB
    public ApplicationBean applicationBean;   
    private final Gson gson;
    
    public AuthController() {
        gson = new Gson();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(String jsonAuth) {
        AuthDto authDto = gson.fromJson(jsonAuth, AuthDto.class);
        boolean hasPermission;
        hasPermission = applicationBean
                .hasPermission(authDto.getToken1(), authDto.getToken2());
        if (hasPermission) {
            return Response
                        .status(Response.Status.OK)
                        .entity("hasPermission")
                        .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("hasPermission")
                    .build();
        }
    }
}
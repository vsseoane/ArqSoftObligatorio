/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roi.supplying;

import com.google.gson.Gson;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Alejandro
 */
@Path("/orders")
public class OrderController {
    
    @Context
    private UriInfo context;
    @EJB
    private OrderBean orderBean;

    private final Gson gson;
    
    public OrderController()
    {    
        gson = new Gson();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(String jsonOrder) {
        Order order = gson.fromJson(jsonOrder, Order.class);
        this.orderBean.update(order);
        return Response.status(Response.Status.OK)
                .entity(gson.toJson(jsonOrder))
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
            List<Order> orders = this.orderBean.getAll();
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(orders))
                    .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(this.orderBean.find(id))).build();
        } catch (OrderNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson(new StringBuilder()
                            .append("Message: ")
                            .append(e.getMessage())
                            .toString()))
                    .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(String jsonOrder) {
            Order order = gson.fromJson(jsonOrder, Order.class);
            this.orderBean.create(order);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(order))
                    .build();
    }

    @DELETE
    public Response delete(){
                    return Response.status(Response.Status.OK)
                    .entity("Not implemented.")
                    .build();
    }
    

}

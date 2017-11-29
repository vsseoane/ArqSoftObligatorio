package com.supplying.Controllers;

import com.google.gson.Gson;
import com.supplying.Authentication.RestAuthenticationBean;
import com.supplying.orders.Order;
import com.supplying.orders.OrderBean;
import com.supplying.orders.OrderNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("order")
public class OrderController {
   @Context
    private UriInfo context;
    @EJB
    private OrderBean orderBean;
    @EJB
    private RestAuthenticationBean restAuthenticationBean;

    private final Gson gson;
    private final String tokenSupplyBkend;
    private final String url;
    
    public OrderController()
    {    
        gson = new Gson();
        tokenSupplyBkend = "bd1315c8-8a5c-4854-82d7-3caeaf0a90de";
        url = "http://localhost:8080/roiAuthentication-war/auth";
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(String jsonOrder,@HeaderParam("token") String token) {
        try{
             boolean isAuthorizated = restAuthenticationBean.postMethod(token,tokenSupplyBkend,url);
             if(isAuthorizated){
                Order order = gson.fromJson(jsonOrder, Order.class);
                 this.orderBean.update(order);
                return Response.status(Response.Status.OK)
                .entity(gson.toJson(jsonOrder))
                .build();
             }else{
                    return Response
                    .status(Response.Status.UNAUTHORIZED).build();
             }
             
        }catch(OrderNotFoundException e){
             Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, e);
             return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado esa orden").build();
        }catch(Exception e){
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Body incorrecto").build();
        }
       
      
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@HeaderParam("token") String token) {
        
        boolean isAuthorizated = restAuthenticationBean.postMethod(token,tokenSupplyBkend,url);
        if(isAuthorizated){
            List<Order> orders = this.orderBean.getAll();
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(orders))
                    .build();
        }else{
             return Response
                    .status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@HeaderParam("id") int id,@HeaderParam("token") String token) {
        try {
             boolean isAuthorizated = restAuthenticationBean.postMethod(token,tokenSupplyBkend,url);
             if(isAuthorizated){
                    return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(this.orderBean.find(id))).build();
             }else{
                    return Response
                    .status(Response.Status.UNAUTHORIZED).build();
             }
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
    public Response post(String jsonOrder,@HeaderParam("token") String token) {
         boolean isAuthorizated = restAuthenticationBean.postMethod(token,tokenSupplyBkend,url);
        if(isAuthorizated){
            Order order = gson.fromJson(jsonOrder, Order.class);
            this.orderBean.create(order);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(order))
                    .build();
        }else{
              return Response
                    .status(Response.Status.UNAUTHORIZED).build();
        }
    }
    @DELETE
     @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam("id") int id) {
           try{
                this.orderBean.delete(id);
                return Response.status(Response.Status.OK).entity("DeleteOrder").build();   
          
            } catch(OrderNotFoundException e){
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, e);
                 return Response.status(Response.Status.NOT_FOUND).entity("No se ha encontrado esa orden").build();
            } 
    }

    
   
}

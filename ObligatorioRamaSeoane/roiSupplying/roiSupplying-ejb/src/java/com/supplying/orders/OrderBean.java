package com.supplying.orders;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class OrderBean {

    
    @PersistenceContext
    protected EntityManager em;
    
    @EJB
    private NotificationBean notificationBean;
    
    
   
    
    public void create (Order order){
        this.em.persist(order);
        RequestDTO request = new RequestDTO();
        request.message = "CREATE";
        request.order = order;
        notificationBean.sendOrderQueueNotification(request);
    }

    public Order find(int id) throws OrderNotFoundException{
        Order order =  em.find(Order.class,id);
        if(order == null)
            throw new OrderNotFoundException();
        return order;
    }
    
    public void update(Order order) throws OrderNotFoundException,OrderNotValidException{
        int orderNumber = order.getOrderNumber();
        if( orderNumber <= 0) {
           throw new OrderNotValidException();
        }
        Order orderFound =  find(orderNumber);
        if(orderFound == null){
             throw new OrderNotFoundException();
        }else{
         em.merge(order);
         RequestDTO request = new RequestDTO();
         request.message = "UPDATE";
         request.order = order;
         notificationBean.sendOrderQueueNotification(request);
        }
    }
    
    public void delete(int idOrder) throws OrderNotFoundException{
       Order order = find(idOrder);
       if (order != null){
           em.remove(order);
           RequestDTO request = new RequestDTO();
           request.message = "DELETE";
           request.order = order;
           notificationBean.sendOrderQueueNotification(request);
       }      
    }
    
    
    public List<Order> getAll() {
        return em.createNativeQuery("select * from Orders",Order.class).getResultList();
    }
}

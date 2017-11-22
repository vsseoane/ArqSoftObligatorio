/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roi.supplying;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alejandro
 */
@Stateless
@LocalBean
public class OrderBean {

    @PersistenceContext
    protected EntityManager em;
       
    public void create (Order order){
        this.em.persist(order);
    }
    
    public Order find(int id) throws OrderNotFoundException{
        Order order =  em.find(Order.class,id);
        if(order == null)
            throw new OrderNotFoundException();
        return order;
    }
    
    public void update(Order order){
         em.merge(order);
    }
     
    public List<Order> getAll() {
        return em.createNativeQuery("select * from Orders",Order.class).getResultList();
    }
}


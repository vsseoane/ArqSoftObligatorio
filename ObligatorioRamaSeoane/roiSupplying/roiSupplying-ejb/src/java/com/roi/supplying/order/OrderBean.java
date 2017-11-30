package com.roi.supplying.order;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class OrderBean {

    
    @PersistenceContext
    protected EntityManager em;
    
    @EJB
    private NotificationBean notificationBean;
    
    /**
     *
     * @param order Order to Create
     */
    public void create(Order order) {
        this.em.persist(order);
        RequestDto request = new RequestDto();
        request.message = "CREATE";
        request.order = order;
        notificationBean.sendOrderQueueNotification(request);
    }

    public Order find(int id) throws OrderNotFoundException {
        Order order = em.find(Order.class, id);
        if (order == null) {
            throw new OrderNotFoundException();
        }
        return order;
    }
    
    public void update(Order order) throws OrderNotFoundException, OrderNotValidException {
        int orderNumber = order.getOrderNumber();
        if ( orderNumber <= 0 ) {
            throw new OrderNotValidException();
        }
        Order orderFound =  find(orderNumber);
        if ( orderFound == null ) {
            throw new OrderNotFoundException();
        } else {
            em.merge(order);
            RequestDto request = new RequestDto();
            request.message = "UPDATE";
            request.order = order;
            notificationBean.sendOrderQueueNotification(request);
        }
    }
    
    public void delete(int idOrder) throws OrderNotFoundException {
        try {
            Order order = (Order) em.createNativeQuery("select * from ORDERS"
                + " where orderNumber=" + idOrder, Order.class).getResultList().get(0);
            if (order != null) {
                em.remove(order);
                RequestDto request = new RequestDto();
                request.message = "DELETE";
                request.order = order;
                notificationBean.sendOrderQueueNotification(request);
            } else {
                throw new OrderNotFoundException();
            }    
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrderNotFoundException();
        }
        
    }
    
    
    public List<Order> getAll() {
        return em.createNativeQuery("select * from Orders", Order.class).getResultList();
    }
}

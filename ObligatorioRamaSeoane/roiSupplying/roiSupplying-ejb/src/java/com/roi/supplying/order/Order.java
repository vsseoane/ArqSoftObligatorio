package com.roi.supplying.order;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * Order is the class to persist
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO) 
    private int orderNumber;
    
    private int clientNumber;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    private int requestedVolume;
    
    private int pointOfServiceIdentifier;
    
    private int billingDay;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getRequestedVolume() {
        return requestedVolume;
    }

    public void setRequestedVolume(int requestedVolume) {
        this.requestedVolume = requestedVolume;
    }

    public int getPointOfServiceIdentifier() {
        return pointOfServiceIdentifier;
    }

    public void setPointOfServiceIdentifier(int pointOfServiceIdentifier) {
        this.pointOfServiceIdentifier = pointOfServiceIdentifier;
    }

    public int getBillingDay() {
        return billingDay;
    }

    public void setBillingDay(int billingDay) {
        this.billingDay = billingDay;
    }
}

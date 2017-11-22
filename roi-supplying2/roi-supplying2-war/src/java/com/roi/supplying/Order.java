/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roi.supplying;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int OrderNumber;
    
    private int ClientNumber;
    
    private Date StartDate;
    
    public int RequestedVolume;
    
    private int PointOfServiceIdentifier;
    
    private int BillingDay;

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public int getClientNumber() {
        return ClientNumber;
    }

    public void setClientNumber(int ClientNumber) {
        this.ClientNumber = ClientNumber;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public int getRequestedVolume() {
        return RequestedVolume;
    }

    public void setRequestedVolume(int RequestedVolume) {
        this.RequestedVolume = RequestedVolume;
    }

    public int getPointOfServiceIdentifier() {
        return PointOfServiceIdentifier;
    }

    public void setPointOfServiceIdentifier(int PointOfServiceIdentifier) {
        this.PointOfServiceIdentifier = PointOfServiceIdentifier;
    }

    public int getBillingDay() {
        return BillingDay;
    }

    public void setBillingDay(int BillingDay) {
        this.BillingDay = BillingDay;
    }

  
}

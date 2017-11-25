package com.roi.planner.planes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Order implements Serializable {

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

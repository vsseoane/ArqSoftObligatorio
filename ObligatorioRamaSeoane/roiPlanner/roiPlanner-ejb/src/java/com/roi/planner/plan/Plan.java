package com.roi.planner.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Plans")
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isApproved;
    private boolean isSecondApproved;
    private boolean isCanceled;
    private int idOrder;
    private int numberPlan;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Stretch> stretches  = new ArrayList<>();
   
    public Plan() {
        this.isApproved = false;
        this.isCanceled = false;
    } 
    
    public boolean isIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isIsSecondApproved() {
        return isSecondApproved;
    }

    public void setIsSecondApproved(boolean isSecondApproved) {
        this.isSecondApproved = isSecondApproved;
    }

   
   
    public List<Stretch> getStretches() {
        return stretches;
    }

    public void setStretches(List<Stretch> stretches) {
        this.stretches = stretches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getNumberPlan() {
        return numberPlan;
    }

    public void setNumberPlan(int numberPlan) {
        this.numberPlan = numberPlan;
    }
    
}

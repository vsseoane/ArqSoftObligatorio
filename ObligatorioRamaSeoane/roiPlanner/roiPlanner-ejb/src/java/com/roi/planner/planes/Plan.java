
package com.roi.planner.planes;

import com.roi.planner.stretches.Stretch;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Plans")
public class Plan implements Serializable {
    public Plan(){
       
   } 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long ID;
   private boolean isApproved;
   private int idOrder;
   private int numberPlan;
   
   @OneToMany(cascade = CascadeType.PERSIST)
   private List<Stretch> stretches  = new ArrayList<>();

   
   
    public List<Stretch> getStretches() {
        return stretches;
    }

    public void setStretches(List<Stretch> stretches) {
        this.stretches = stretches;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }


    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public int getIdOrder() {
        return idOrder;
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

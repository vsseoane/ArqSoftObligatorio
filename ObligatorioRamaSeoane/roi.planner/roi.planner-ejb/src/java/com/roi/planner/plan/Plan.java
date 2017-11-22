 
package com.roi.planner.plan;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pans")
public class Plan implements Serializable {
   public Plan(){
       
   } 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int ID;
  
   private String idPlan;
   private boolean isApproved;
   private int idOrder;
   private int numberPlan;
   @OneToMany(fetch = FetchType.EAGER)
   private List<Stretch> stretches;

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
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
//si es fecha hay que poner temporal y el tipo que es date
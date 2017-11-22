
package com.roi.planner.plan;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PlanBean {

    
   @PersistenceContext
    private EntityManager em;
   
   
    public List<Plan> getPlans() {
        return em.createQuery("select p from PLAN p").getResultList();
    }

    /*public Plan findPlanById(String id) {
        return em.find(Plan.class, id);
    }*/

    public void createPlan(Plan plan) {
        em.persist(plan);
    }
    
    
}


package com.roi.planner.planes;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.roi.planner.stretches.Stretch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ejb.EJB;



@Stateless
@LocalBean
public class PlanBean {

    @PersistenceContext
    protected EntityManager em;
   
    private Gson gson = new Gson();
   
    public List<Plan> getPlans() {
          
        return em.createNativeQuery("select * from Plans ",Plan.class).getResultList();
    }
    public void createPlanFromOrder(Order order) {
        Plan plan = new Plan();
        plan.setIdOrder(order.getOrderNumber());
        plan.setStretches(getStretches());
        addPlan(plan);       
    }
    public void deletePlanFromOrder(Order order)throws OrderNotFoundException{
         try{
            Plan plan = findOrder(order.getOrderNumber()).get(0);
            if(plan != null){
                 plan.setIsCanceled(true);
                 update(plan);
            }else{
                 throw new OrderNotFoundException();
            }
           
        }catch(OrderNotFoundException e){
            throw new OrderNotFoundException();
        }
    }
    public void updatePlanFromOrder(Order order) throws OrderNotFoundException{
        try{
            Plan plan = findOrder(order.getOrderNumber()).get(0);
            update(plan);
        }catch(OrderNotFoundException e){
            throw new OrderNotFoundException();
        }        
    }
    public List<Plan> findOrder(int id) throws OrderNotFoundException{
        return em.createNativeQuery("select * from PLAN where IDORDER="+ id,Plan.class).getResultList();
    }
    public Plan find(int id) throws OrderNotFoundException{
       try{
        Plan plan =  (Plan)em.createNativeQuery("select * from Plans where ID="+ id,Plan.class).getResultList().get(0);
        return plan;
       }catch(Exception e){
           throw new OrderNotFoundException();
       }
    }
    
        
         
    public void addPlan(Plan plan){
        em.persist(plan);
    }

     public void update(Plan plan) {
        em.merge(plan);
    }
    public void delete(Plan plan) {
           em.remove(plan);
    }
    public List<Stretch> getStretches(){
        try{
            List<Stretch> stretches = new ArrayList<>();
             Client client = ClientBuilder.newClient();
            String jsonStretch = client.target("https://pipeline-calculator-api.herokuapp.com/pipeline-route/service/"+1)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null),String.class); 
            HashMap var = gson.fromJson(jsonStretch,HashMap.class);
            ArrayList<LinkedTreeMap> lista = (ArrayList<LinkedTreeMap>) var.get("sections");
            for(LinkedTreeMap map: lista){
                Stretch stretch = new Stretch();
                stretch.setActuatorId(map.get("actuatorId").toString());
                stretch.setSectionId(map.get("sourceId").toString());
                stretch.setSourceId(map.get("sectionId").toString());
                stretches.add((stretch));
            }
            return stretches;
        }catch(Exception e){
            throw e;
        }
    }
    public void approvePlan(int idPlan) throws PlanNotFoundException{
        try{
            Plan plan = find(idPlan);
            plan.setIsApproved(true);
            update(plan);
        }catch(OrderNotFoundException e){
            throw new PlanNotFoundException();
        }
        
    }
    
}

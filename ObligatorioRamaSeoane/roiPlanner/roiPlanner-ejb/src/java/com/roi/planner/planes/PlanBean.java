
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



@Stateless
@LocalBean
public class PlanBean {
 @PersistenceContext
    private EntityManager em;
    private Gson gson = new Gson();
   
    public List<Plan> getPlans() {
          
        return em.createNativeQuery("select * from Plans ",Plan.class).getResultList();
    }
    public Plan createPlan(Plan plan) {
        em.persist(plan);
        return plan;
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
   
    
}

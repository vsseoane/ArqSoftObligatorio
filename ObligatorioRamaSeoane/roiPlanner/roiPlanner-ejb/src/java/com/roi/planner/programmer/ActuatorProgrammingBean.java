package com.roi.planner.programmer;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ActuatorProgrammingBean {

    @PersistenceContext
    protected EntityManager em;
   
    public void actuatorProgramming(ActuatorProgramming actuatorProgramming) 
            throws ActuatorNotFoundException {
        
        boolean existsActuator = existActuator(actuatorProgramming
                .getPlanId(), actuatorProgramming.getActuatorId());
        boolean isValid = validateCommands(actuatorProgramming.getCommands());
        if (isValid && existsActuator) {
            addPlan(actuatorProgramming);
        } else {
            throw new ActuatorNotFoundException();
        }
    }
             
    public void addPlan(ActuatorProgramming actuator) {
        em.persist(actuator);
    }
    
    public boolean validateCommands(List<CommandValue> commandsValue) {
        boolean isValid = true;
        for (CommandValue commandValue : commandsValue) {
            if (!existCommand(commandValue.getCommand())) {
                isValid = false;
            }
        }
        return isValid;
    }
    
    public boolean existCommand(String command) {
      
        Command commandFound =  findCommand(command);
        return commandFound != null;        
    }
    
    public Command findCommand(String command) {
        try {
            Command commandReturn; 
            commandReturn = (Command) em.createNativeQuery("select * " 
                    + "from Command where NAME='" + command + "'", Command.class)
                    .getResultList().get(0);
            return commandReturn;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean existActuator(int planId, String actuatorId) {
        boolean exist = false;
        try {
            ActuatorProgramming actuator = this.find(planId, actuatorId);
            if (actuator != null) {
                exist = true;
            }
        } catch (ActuatorNotFoundException e) {
            exist = false;
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }
    
    public ActuatorProgramming find(int planId, String id) throws
              ActuatorNotFoundException {
        try {
            ActuatorProgramming actuatorProgramming =  (ActuatorProgramming)
                em.createNativeQuery("SELECT * FROM PLANS as p, PLANS_STRETCH "
                        + "as ps, STRETCH as s where p.ID = " + planId + " and"
                        + " p.ID= ps.PLAN_ID and s.ID = "
                        + "ps.STRETCHES_ID\n" 
                        + "and p.ISAPPROVED = 1 and s.ACTUATORID ='" 
                        + id + "'", ActuatorProgramming.class)
                        .getResultList().get(0);
            return actuatorProgramming;
        } catch (Exception e) {
            throw new ActuatorNotFoundException();
        }
    }
}

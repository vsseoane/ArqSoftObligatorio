
package com.roi.planner.programmer;

import com.roi.planner.planes.OrderNotFoundException;
import com.roi.planner.planes.Plan;
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
   
    public void actuatorProgramming(ActuatorProgramming actuatorProgramming) throws ActuatorNotFoundException{
        boolean existsActuator = existActuator(actuatorProgramming.getActuatorId());
        boolean isValid = validateCommands(actuatorProgramming.getCommands());
        if(isValid && existsActuator){
            addPlan(actuatorProgramming);
        }else{
            throw new ActuatorNotFoundException();
        }
    }
             
    public void addPlan(ActuatorProgramming actuator){
        em.persist(actuator);
    }
    public boolean validateCommands(List<CommandValue> commandsValue){
        boolean isValid = true;
        for(CommandValue aCommandValue: commandsValue){
            if(!existCommand(aCommandValue.getCommand())  || !isTypeValid(aCommandValue)){
                isValid = false;
            }
        }
        return isValid;
    }
    public boolean existCommand(String command){
      
        Command commandFound =  findCommand(command);
        if(commandFound != null){
            return true;
        }else{
            return false;
        }
    }
    public Command findCommand(String command){
        try{
            Command commandReturn = (Command)em.createNativeQuery("select * from Command where NAME='"+ command + "'",Command.class).getResultList().get(0);
            return commandReturn;
        }catch(Exception e){
            return null;
        }
    }
    public boolean isTypeValid(CommandValue aCommandValue){
       /* Command command = findCommand(aCommandValue.getCommand());
        boolean isValid = false;
        if(command.getTypeValueCommand().equals("String")){
            isValid =  aCommandValue.getValue() instanceof String;
        }
        else if(command.getTypeValueCommand().equals("double")){
            isValid =  aCommandValue.getValue() instanceof Double;
         }
         else if(command.getTypeValueCommand().equals("long")){
            isValid =  aCommandValue.getValue() instanceof long;
         }
         else if(command.getTypeValueCommand().equals("datetime")){
            isValid =  aCommandValue.getValue() instanceof datetime;
         }else{
             isValid = false;
         }
        
        return isValid;*/
       return true;
    }
    public boolean existActuator(String actuatorId){
       boolean exist = false;
        try{
            ActuatorProgramming actuator = this.find(actuatorId);
            if(actuator != null)
                exist = true;
        }catch(ActuatorNotFoundException e){
           exist = false;
        }
        catch(Exception e){
            exist = false;
        }
        return exist;
    }
      public ActuatorProgramming find(String id) throws ActuatorNotFoundException{
       try{
        ActuatorProgramming actuatorProgramming =  (ActuatorProgramming)
                em.createNativeQuery("SELECT * FROM PLANS as p, PLANS_STRETCH as ps, STRETCH as s where p.ID= ps.PLAN_ID and s.ID = ps.STRETCHES_ID\n" +
                                "and p.ISAPPROVED = 1 and s.ACTUATORID ='" +id + "'",ActuatorProgramming.class).
                        getResultList().get(0);
        return actuatorProgramming;
       }catch(Exception e){
           throw new ActuatorNotFoundException();
       }
    }
}

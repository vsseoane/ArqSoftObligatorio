package com.roi.planner.planes;

import com.google.gson.Gson;
import com.roi.planner.planes.dto.RequestDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/SupplyingQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SupplyMessageDriven implements MessageListener {
    @EJB
    private PlanBean pleanBean;
    
    public SupplyMessageDriven() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Gson gson = new Gson();
            ObjectMessage obj = (ObjectMessage) message;
            String jsonRequestDTO = obj.getObject().toString();
            RequestDTO requestDTO = gson.fromJson(jsonRequestDTO, RequestDTO.class);
            Order order = requestDTO.getOrder();
            String action = requestDTO.getMessage();
            if(action.equals("CREATE")){
                pleanBean.createPlanFromOrder(order);
            }
            if(action.equals("UPDATE")){
                pleanBean.updatePlanFromOrder(order);
            }
            if(action.equals("DELETE")){
                pleanBean.deletePlanFromOrder(order);
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplyMessageDriven.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

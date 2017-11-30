package com.roi.planner.plan;

import com.google.gson.Gson;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", 
            propertyValue = "jms/SupplyingQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", 
                propertyValue = "javax.jms.Queue")
    })
public class SupplyMessageDriven implements MessageListener {
    @EJB
    private PlanBean planBean;
    
    public SupplyMessageDriven() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Gson gson = new Gson();
            ObjectMessage obj = (ObjectMessage) message;
            String jsonRequestDto = obj.getObject().toString();
            RequestDto requestDto = gson.fromJson(jsonRequestDto, RequestDto.class);
            Order order = requestDto.getOrder();
            String action = requestDto.getMessage();
            if (action.equals("CREATE")) {
                planBean.createPlanFromOrder(order);
            }
            if (action.equals("UPDATE")) {
                planBean.updatePlanFromOrder(order);
            }
            if (action.equals("DELETE")) {
                planBean.deletePlanFromOrder(order);
            }
        } catch (OrderNotFoundException ex) {
            Logger.getLogger(SupplyMessageDriven.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(SupplyMessageDriven.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

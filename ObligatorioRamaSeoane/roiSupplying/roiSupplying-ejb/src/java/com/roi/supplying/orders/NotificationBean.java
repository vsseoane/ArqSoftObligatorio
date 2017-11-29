package com.roi.supplying.orders;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
@LocalBean
public class NotificationBean {

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/SupplyingQueue")
    private Queue queue;
    
    
    public void sendOrderQueueNotification(RequestDto requestDto) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            Gson gson = new Gson();
            String gsonRequestDto = gson.toJson(requestDto);
            ObjectMessage msg = session.createObjectMessage(gsonRequestDto);
            MessageProducer producer = session.createProducer(queue);
            producer.send(msg);
            session.close();
            connection.close();
        } catch (JMSException e) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

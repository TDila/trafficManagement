package com.vulcan.trafficmanagement.message;

import com.google.gson.Gson;
import com.vulcan.trafficmanagement.model.IoTDevice;
import com.vulcan.trafficmanagement.remote.DataStorage;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",propertyValue = "trafficQueue")
})
public class MessageReceiver implements MessageListener {
    @EJB
    DataStorage dataStorage;
    @Override
    public void onMessage(Message message) {
        try{
            String messageBody = message.getBody(String.class);
            IoTDevice ioTDevice = new Gson().fromJson(messageBody,IoTDevice.class);
            dataStorage.storeData(ioTDevice);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}

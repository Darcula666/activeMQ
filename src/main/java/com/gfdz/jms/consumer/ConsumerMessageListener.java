package com.gfdz.jms.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener{
    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        System.out.println("接收消息"+textMessage);
    }
}

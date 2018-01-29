package com.gfdz.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {
    private static final String url = "tcp://localhost:61616";
    private static final String topicName = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.创建 connection
        Connection connection = connectionFactory.createConnection();
        //3.启动connection
        connection.start();
        //4.创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目标
        Destination destination  = session.createTopic(topicName);
        //6.创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i <100 ; i++) {
            //7.创建消息
            TextMessage textMessage=session.createTextMessage("test"+i);
            producer.send(textMessage);
            System.out.println("发送消息"+textMessage.getText());
        }
        //关闭连接
        connection.close();
    }
}


package com.gfdz.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * http://localhost:8161   admin/admin
 */
public class AppConsumer {
    private static final String url = "tcp://localhost:61616";
    private static final String TopticName = "queue-test";
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
        Destination destination  = session.createTopic(TopticName);
        //6.创建消费者
      MessageConsumer consumer=  session.createConsumer(destination);
      //7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("接收消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //关闭连接
        //connection.close();
    }
}

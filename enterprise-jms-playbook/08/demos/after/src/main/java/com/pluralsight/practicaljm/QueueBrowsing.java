package com.pluralsight.practicaljm;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 */
public class QueueBrowsing {

    public static void main(String... args) throws JMSException{

        ConnectionFactory cf =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection =
                cf.createConnection();
        Session session =
                connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        Queue queue = session.createQueue("ActiveMQ.DLQ");
        browseQueue(session, queue);
        session.close();
        connection.close();

    }


    private static void browseQueue(Session session, Queue queue) throws JMSException {
        QueueBrowser queueBrowser = session.createBrowser(queue);
        Enumeration enumeration = queueBrowser.getEnumeration();
        int count = 0;
        while (enumeration.hasMoreElements()) {
            Message message = (Message)enumeration.nextElement();
            if (message instanceof TextMessage) {
                System.out.println("Message: " + ((TextMessage) message).getText());
            }
            count++;
        }
        queueBrowser.close();
        System.out.println("Number of messages on queue "
                + queue.getQueueName()
                + "="
                + count);
    }
}

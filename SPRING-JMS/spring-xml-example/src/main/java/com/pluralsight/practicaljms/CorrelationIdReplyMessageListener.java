package com.pluralsight.practicaljms;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Grant Little grant@grantlittle.me
 */
public class CorrelationIdReplyMessageListener implements MessageListener {

    private JmsTemplate jmsTemplate;
    private MessageConverter messageConverter;

    @Required
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Required
    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void onMessage(Message inboundMessage) {
        try {
            Request request = (Request) messageConverter.fromMessage(inboundMessage);
            Response response = new Response();
            response.setId(request.getId());
            jmsTemplate.convertAndSend("RESPONSE_QUEUE", response, responseMessage -> {
                responseMessage.setJMSCorrelationID(inboundMessage.getJMSMessageID());
                return responseMessage;
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

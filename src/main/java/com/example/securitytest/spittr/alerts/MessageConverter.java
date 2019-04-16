package com.example.securitytest.spittr.alerts;

import org.springframework.messaging.converter.MessageConversionException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by hx on 2019-04-16.
 */
public interface MessageConverter {
    Message toMessage(Object object, Session session) throws JMSException, MessageConversionException;

    Object fromMessage(Message message) throws JMSException, MessageConversionException;
}

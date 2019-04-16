package com.example.securitytest.spittr.alerts;

import com.example.securitytest.spittr.domain.Spittle;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by hx on 2019-04-16.
 */
//public class AlertServiceImpl implements AlertService {
//    private JmsOperations jmsOperations;
//
//    @Autowired
//    public AlertServiceImpl(JmsOperations jmsOperations) {
//        this.jmsOperations = jmsOperations;
//    }
//
//    public void sendSpittleAlert(final Spittle spittle) {
//        jmsOperations.convertAndSend(spittle);
//    }
//
//    public Spittle receiveSpittleAlert() {
//        try {
//            ObjectMessage receiveMessage = (ObjectMessage) jmsOperations.receive();
//            return (Spittle) receiveMessage.getObject();
//        } catch (JMSException je) {
//            throw JmsUtils.convertJmsAccessException(je);
//        }
//    }
//
//    public Spittle retrieveSpittleAlert() {
//        return (Spittle) jmsOperations.receiveAndConvert();
//    }
//
//}
//@Component("alertService")
//public class AlertServiceImpl implements AlertService {
//    private JavaMailSender mailSender;
//    private String alertEmailAddress;
//
//    public AlertServiceImpl(JavaMailSender javaMailSender, String alertEmailAddress) {
//        this.mailSender = javaMailSender;
//        this.alertEmailAddress = alertEmailAddress;
//    }
//
//    public void sendSpittleAlert(final Spittle spittle) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        String spitterName = spittle.getSpitter().getFullName();
//        message.setFrom("noreply@spitter.com");
//        message.setTo(alertEmailAddress);
//        message.setSubject("New spittle from " + spitterName);
//        message.setText(spitterName + " says: " + spittle.getText());
//        mailSender.send(message);
//    }
//
//}
@Component("alertService")
public class AlertServiceImpl implements AlertService {
    private RabbitTemplate rabbit;

    @Autowired
    public AlertServiceImpl(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendSpittleAlert(Spittle spittle) {
        rabbit.convertAndSend("Spittle.alert.exchange", "spittle.alerts", spittle);
    }

}
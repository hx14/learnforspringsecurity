package com.example.securitytest.spittr.alerts;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by hx on 2019-04-16.
 */
@MessageDriven
public class SpittleAlertHandler implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    public void onMessage(Message message) {

    }
}

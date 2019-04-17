package com.example.securitytest.marcopolo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by hx on 2019-04-17.
 */
@Controller
public class MarcoController {
    private static final Logger logger = LoggerFactory.getLogger(MarcoController.class);
    @MessageMapping("/marco")
    public void handleShout(Shout incoming) {
        logger.info("Receiced message: " + incoming.getMessage());
    }
    @SubscribeMapping({"/marco"})
    public  Shout handleSubscription() {
        Shout outgoing = new Shout();
        outgoing.setMessage("Polo!");
        return outgoing;
    }
}

package com.example.securitytest.spittr;

import com.example.securitytest.spittr.data.SpittleRespository;
import com.example.securitytest.spittr.domain.Spittle;
import com.example.securitytest.spittr.web.SpittleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;

import javax.management.Notification;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hx on 2019-04-17.
 */
public class SpittleFeedServiceImpl implements SpittleFeedService {
    private Pattern pattern = Pattern.compile("\\@(\\$+)");
    private SimpMessagingTemplate messaging;
    private final Logger logger = LoggerFactory.getLogger(SpittleException.class);
    @Autowired
    private SpittleRespository spittleRespository;

    @Autowired
    public SpittleFeedServiceImpl(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }

    public void broadcastSpittle(Spittle spittle) {
        messaging.convertAndSend("/topic/spittlefeed", spittle);
        Matcher matcher = pattern.matcher(spittle.getMessage());
        if (matcher.find()) {
            String username = matcher.group(1);
            messaging.convertAndSendToUser(username, "/queue/notifications",
                    new Notification("You just got mentioned!", 0,0));
        }
    }
    @MessageExceptionHandler(SpittleException.class)
    @SendToUser("/queue/errors")
    public SpittleException handleException(SpittleException e ) {
        logger.error("Error handling message: " + e.getMessage());
        return e;
    }
}

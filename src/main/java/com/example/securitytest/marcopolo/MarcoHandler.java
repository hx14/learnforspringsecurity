package com.example.securitytest.marcopolo;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by hx on 2019-04-17.
 */
    // 第一种方法,继承AbstractWebSocketHandler
//public class MarcoHandler extends AbstractWebSocketHandler {
//    private static final Logger logger = LoggerFactory.getLogger(MarcoHandler.class);
//
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        logger.info("Received message: " + message.getPayload());
//        Thread.sleep(2000);
//        session.sendMessage(new TextMessage("Polo!"));
//    }
//}
    // 第一种方法,继承TextWebSocketHandler,TextWebSocketHandler是AbstractWebSocketHandler的子类,拒绝二进制消息
    // 重载了方法handleBinaryMessage(),如果收到二进制消息,则关闭WebSocket连接
public class MarcoHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MarcoHandler.class);

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received message: " + message.getPayload());
        Thread.sleep(2000);
        session.sendMessage(new TextMessage("Polo!"));
    }
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        logger.info("Connection established");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection closed. Status: " + status);
    }
}
package com.example.securitytest.marcopolo;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by hx on 2019-04-17.
 */
public interface AbstractWebSocketHandler {
    void afterConnectionEstablished(WebSocketSession session) throws Exception;
    void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception;
    void handlerTransporterror(WebSocketSession session, Throwable exception) throws Exception;
    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception;
    boolean supportsPartialMessage();
}

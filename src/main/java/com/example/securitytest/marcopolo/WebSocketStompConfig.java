package com.example.securitytest.marcopolo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by hx on 2019-04-17.
 */
@Configuration
@EnableWebSocketMessageBroker // 启用STOMP消息
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/marcopolo").withSockJS(); // 为"/marcopolo"路径启用SockJS功能
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/queue", "/topic");
//        registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost("rabbit.someotherserver") // 可单独修改中继端口
                .setRelayPort(62623)// 默认是61613
                .setClientLogin("marcopolo") // 默认是guest
                .setClientPasscode("letmein01"); // 默认是guest
        registry.setApplicationDestinationPrefixes("/app", "/foo");
    }
}
